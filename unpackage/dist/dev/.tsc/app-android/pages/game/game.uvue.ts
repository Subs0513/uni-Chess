import { ref, computed } from 'vue'

/* 行列 */
type Turn = 'white' | 'black'
type Board = (string | null)[][]


const __sfc__ = defineComponent({
  __name: 'game',
  setup(__props) {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

const rows = [0,1,2,3,4,5,6,7]
const cols = [0,1,2,3,4,5,6,7]

/* ========= ✅ 尺寸：修复“棋盘显示不全” =========
   总宽 = boardPx + coordPx
   所以 boardPx 必须用 (可用宽度 - coordPx) 来算
   coordPx 又依赖 cellPx，所以用 2~3 次迭代收敛
*/
const boardPx = ref<number>(320)
const coordPx = ref<number>(22)

function clamp(n: number, minV: number, maxV: number): number {
  if (n < minV) return minV
  if (n > maxV) return maxV
  return n
}

function calcCoordFromCell(cell: number): number {
  let v = Math.floor(cell * 0.55)
  if (v < 18) v = 18
  if (v > 26) v = 26
  return v
}

function recalcSizes() {
  const info: GetSystemInfoResult = uni.getSystemInfoSync()
  const ww: number = info.windowWidth

  // 页面左右 padding 共 36（page padding 18px*2）
  const maxTotal = ww - 36

  // 初始假设
  let c = 22
  let b = maxTotal - c

  // 迭代两三次，让 coordPx 与 cellPx 一致
  for (let i = 0; i < 3; i++) {
    // board 约束范围 + 对齐 8 的倍数
    b = clamp(b, 240, 360)
    b = b - (b % 8)

    const cell = b / 8
    c = calcCoordFromCell(cell)

    // 保证总宽不超
    b = maxTotal - c
  }

  // 最终再对齐一次
  b = clamp(b, 240, 360)
  b = b - (b % 8)
  c = calcCoordFromCell(b / 8)

  boardPx.value = b
  coordPx.value = c
}

recalcSizes()

const cellPx = computed((): number => boardPx.value / 8)

/* style 字符串 */
const boardStyle = computed((): string => {
  const px = boardPx.value.toString() + 'px'
  return 'width:' + px + ';height:' + px + ';'
})

const rowStyle = computed((): string => {
  const w = boardPx.value.toString() + 'px'
  const h = cellPx.value.toString() + 'px'
  return 'width:' + w + ';height:' + h + ';'
})

const cellStyle = computed((): string => {
  const px = cellPx.value.toString() + 'px'
  return 'width:' + px + ';height:' + px + ';flex:0 0 ' + px + ';'
})

/* 外框（含坐标） */
const frameStyle = computed((): string => {
  const w = (boardPx.value + coordPx.value).toString() + 'px'
  const h = (boardPx.value + coordPx.value).toString() + 'px'
  return 'width:' + w + ';height:' + h + ';'
})

const ranksColStyle = computed((): string => {
  const w = coordPx.value.toString() + 'px'
  const h = boardPx.value.toString() + 'px'
  return 'width:' + w + ';height:' + h + ';'
})

const rankCellStyle = computed((): string => {
  const w = coordPx.value.toString() + 'px'
  const h = cellPx.value.toString() + 'px'
  return 'width:' + w + ';height:' + h + ';'
})

const filesRowStyle = computed((): string => {
  const w = (boardPx.value + coordPx.value).toString() + 'px'
  const h = coordPx.value.toString() + 'px'
  return 'width:' + w + ';height:' + h + ';'
})

const filesSpacerStyle = computed((): string => {
  const w = coordPx.value.toString() + 'px'
  const h = coordPx.value.toString() + 'px'
  return 'width:' + w + ';height:' + h + ';'
})

const fileCellStyle = computed((): string => {
  const w = cellPx.value.toString() + 'px'
  const h = coordPx.value.toString() + 'px'
  return 'width:' + w + ';height:' + h + ';'
})

function rankLabel(r: number): string { return (8 - r).toString() }
function fileLabel(c: number): string { return String.fromCharCode(97 + c) }

/* ========= 对弈 ========= */
const turn = ref<Turn>('white')

class Pos { r: number; c: number; constructor(r: number, c: number){ this.r=r; this.c=c } }
const selected = ref<Pos | null>(null)

/* Unicode */
const BR='♜', BN='♞', BB='♝', BQ='♛', BK='♚', BP='♟'
const WR='♖', WN='♘', WB='♗', WQ='♕', WK='♔', WP='♙'

function createInitBoard(): Board {
  return [
    [BR, BN, BB, BQ, BK, BB, BN, BR],
    [BP, BP, BP, BP, BP, BP, BP, BP],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [null, null, null, null, null, null, null, null],
    [WP, WP, WP, WP, WP, WP, WP, WP],
    [WR, WN, WB, WQ, WK, WB, WN, WR]
  ]
}
const board = ref<Board>(createInitBoard())

function pieceAt(r:number,c:number):string{
  const p = board.value[r][c]
  return p==null?'':p
}

function isWhitePiece(p:string):boolean{ return p==WR||p==WN||p==WB||p==WQ||p==WK||p==WP }
function isBlackPiece(p:string):boolean{ return p==BR||p==BN||p==BB||p==BQ||p==BK||p==BP }
function colorOf(p:string):Turn{ return isWhitePiece(p)?'white':'black' }

/* ===== 合法走子：基础规则 + 不送将 ===== */
function inBounds(r:number,c:number):boolean{ return r>=0&&r<8&&c>=0&&c<8 }

function cloneBoard(b:Board):Board{
  const nb:Board=[]
  for(let i=0;i<8;i++){
    const nr:(string|null)[]=[]
    for(let j=0;j<8;j++) nr.push(b[i][j])
    nb.push(nr)
  }
  return nb
}

function isEmpty(b:Board,r:number,c:number):boolean{ return b[r][c]==null }
function isEnemyAt(b:Board,r:number,c:number,me:Turn):boolean{
  const p=b[r][c]; if(p==null) return false
  return (me=='white')?isBlackPiece(p):isWhitePiece(p)
}

function rayClear(b:Board,sr:number,sc:number,dr:number,dc:number):boolean{
  const dR=dr-sr, dC=dc-sc
  const stepR=dR==0?0:(dR>0?1:-1)
  const stepC=dC==0?0:(dC>0?1:-1)
  let r=sr+stepR, c=sc+stepC
  while(r!=dr || c!=dc){
    if(!isEmpty(b,r,c)) return false
    r+=stepR; c+=stepC
  }
  return true
}

function pieceType(p:string):string{
  if(p==WP||p==BP) return 'P'
  if(p==WN||p==BN) return 'N'
  if(p==WB||p==BB) return 'B'
  if(p==WR||p==BR) return 'R'
  if(p==WQ||p==BQ) return 'Q'
  return 'K'
}

function findKing(b:Board,who:Turn):Pos|null{
  const k=(who=='white')?WK:BK
  for(let r=0;r<8;r++){
    for(let c=0;c<8;c++){
      if(b[r][c]==k) return new Pos(r,c)
    }
  }
  return null
}

function isSquareAttacked(b:Board,tr:number,tc:number,by:Turn):boolean{
  // pawn
  if(by=='white'){
    const r=tr+1
    if(inBounds(r,tc-1)&&b[r][tc-1]==WP) return true
    if(inBounds(r,tc+1)&&b[r][tc+1]==WP) return true
  }else{
    const r=tr-1
    if(inBounds(r,tc-1)&&b[r][tc-1]==BP) return true
    if(inBounds(r,tc+1)&&b[r][tc+1]==BP) return true
  }
  // knight
  const kd=[[-2,-1],[-2,1],[-1,-2],[-1,2],[1,-2],[1,2],[2,-1],[2,1]]
  for(let i=0;i<kd.length;i++){
    const rr=tr+kd[i][0], cc=tc+kd[i][1]
    if(!inBounds(rr,cc)) continue
    const p=b[rr][cc]; if(p==null) continue
    if(by=='white'&&p==WN) return true
    if(by=='black'&&p==BN) return true
  }
  // king
  for(let rr=tr-1;rr<=tr+1;rr++){
    for(let cc=tc-1;cc<=tc+1;cc++){
      if(rr==tr&&cc==tc) continue
      if(!inBounds(rr,cc)) continue
      const p=b[rr][cc]; if(p==null) continue
      if(by=='white'&&p==WK) return true
      if(by=='black'&&p==BK) return true
    }
  }
  // rook/queen rays
  const rd=[[-1,0],[1,0],[0,-1],[0,1]]
  for(let i=0;i<rd.length;i++){
    let rr=tr+rd[i][0], cc=tc+rd[i][1]
    while(inBounds(rr,cc)){
      const p=b[rr][cc]
      if(p!=null){
        if(by=='white'&&(p==WR||p==WQ)) return true
        if(by=='black'&&(p==BR||p==BQ)) return true
        break
      }
      rr+=rd[i][0]; cc+=rd[i][1]
    }
  }
  // bishop/queen rays
  const bd=[[-1,-1],[-1,1],[1,-1],[1,1]]
  for(let i=0;i<bd.length;i++){
    let rr=tr+bd[i][0], cc=tc+bd[i][1]
    while(inBounds(rr,cc)){
      const p=b[rr][cc]
      if(p!=null){
        if(by=='white'&&(p==WB||p==WQ)) return true
        if(by=='black'&&(p==BB||p==BQ)) return true
        break
      }
      rr+=bd[i][0]; cc+=bd[i][1]
    }
  }
  return false
}

function isKingInCheck(b:Board,who:Turn):boolean{
  const k=findKing(b,who)
  if(k==null) return false
  const opp:Turn=(who=='white')?'black':'white'
  return isSquareAttacked(b,k.r,k.c,opp)
}

function canMovePieceRaw(b:Board,sr:number,sc:number,dr:number,dc:number,who:Turn):boolean{
  if(!inBounds(sr,sc)||!inBounds(dr,dc)) return false
  if(sr==dr&&sc==dc) return false
  const p=b[sr][sc]; if(p==null) return false
  if(colorOf(p)!=who) return false
  const dst=b[dr][dc]
  if(dst!=null && colorOf(dst)==who) return false

  const t=pieceType(p)
  const dR=dr-sr, dC=dc-sc
  const absR=Math.abs(dR), absC=Math.abs(dC)

  if(t=='P'){
    const dir=(who=='white')?-1:1
    const startRow=(who=='white')?6:1
    if(dC==0){
      if(dR==dir && isEmpty(b,dr,dc)) return true
      if(sr==startRow && dR==2*dir){
        const mid=sr+dir
        if(isEmpty(b,mid,sc)&&isEmpty(b,dr,dc)) return true
      }
      return false
    }
    if(absC==1 && dR==dir){
      return isEnemyAt(b,dr,dc,who)
    }
    return false
  }

  if(t=='N'){
    return (absR==2&&absC==1)||(absR==1&&absC==2)
  }

  if(t=='B'){
    if(absR!=absC) return false
    return rayClear(b,sr,sc,dr,dc)
  }

  if(t=='R'){
    if(!(dR==0||dC==0)) return false
    return rayClear(b,sr,sc,dr,dc)
  }

  if(t=='Q'){
    const ok=(absR==absC)||(dR==0||dC==0)
    if(!ok) return false
    return rayClear(b,sr,sc,dr,dc)
  }

  // K
  return absR<=1 && absC<=1
}

function isLegalMove(b:Board,sr:number,sc:number,dr:number,dc:number,who:Turn):boolean{
  if(!canMovePieceRaw(b,sr,sc,dr,dc,who)) return false
  const nb=cloneBoard(b)
  nb[dr][dc]=nb[sr][sc]
  nb[sr][sc]=null
  if(isKingInCheck(nb,who)) return false
  return true
}

/* ===== 交互 ===== */
function onTapCell(r:number,c:number){
  const piece=board.value[r][c]

  if(selected.value==null){
    if(piece==null) return
    if(turn.value=='white'&&isWhitePiece(piece)) selected.value=new Pos(r,c)
    else if(turn.value=='black'&&isBlackPiece(piece)) selected.value=new Pos(r,c)
    return
  }

  const sr=selected.value.r, sc=selected.value.c
  const moving=board.value[sr][sc]
  if(moving==null){ selected.value=null; return }

  // 点到己方子：改选
  if(piece!=null && (
    (turn.value=='white' && isWhitePiece(piece)) ||
    (turn.value=='black' && isBlackPiece(piece))
  )){
    selected.value=new Pos(r,c)
    return
  }

  const ok=isLegalMove(board.value,sr,sc,r,c,turn.value)
  if(!ok){
    uni.showToast({ title:'非法走法', icon:'none' })
    return
  }

  board.value[r][c]=moving
  board.value[sr][sc]=null
  selected.value=null
  turn.value=(turn.value=='white')?'black':'white'
}

function cellClass(r:number,c:number):string{
  let cls=((r+c)%2==1)?'dark':'light'
  if(selected.value!=null && selected.value.r==r && selected.value.c==c){
    cls=cls+' selected'
  }
  return cls
}

const selectedText = computed(():string=>{
  if(selected.value==null) return '无'
  const file=String.fromCharCode(97+selected.value.c)
  const rank=(8-selected.value.r).toString()
  return file+rank
})

const turnText = computed(():string => (turn.value=='white'?'白方回合':'黑方回合'))

function resetBoard(){
  board.value=createInitBoard()
  selected.value=null
  turn.value='white'
}

return (): any | null => {

  return _cE("view", _uM({ class: "page" }), [
    _cE("view", _uM({ class: "head" }), [
      _cE("text", _uM({ class: "h1" }), "Game"),
      _cE("text", _uM({ class: "sub" }), _tD(turnText.value), 1 /* TEXT */)
    ]),
    _cE("view", _uM({ class: "boardWrap" }), [
      _cE("view", _uM({
        class: "boardFrame",
        style: _nS(frameStyle.value)
      }), [
        _cE("view", _uM({ class: "boardRowWrap" }), [
          _cE("view", _uM({
            class: "ranksCol",
            style: _nS(ranksColStyle.value)
          }), [
            _cE(Fragment, null, RenderHelpers.renderList(rows, (r, __key, __index, _cached): any => {
              return _cE("view", _uM({
                key: 'rank_' + r,
                class: "rankCell",
                style: _nS(rankCellStyle.value)
              }), [
                _cE("text", _uM({ class: "coordText" }), _tD(rankLabel(r)), 1 /* TEXT */)
              ], 4 /* STYLE */)
            }), 64 /* STABLE_FRAGMENT */)
          ], 4 /* STYLE */),
          _cE("view", _uM({
            class: "board",
            style: _nS(boardStyle.value)
          }), [
            _cE(Fragment, null, RenderHelpers.renderList(rows, (r, __key, __index, _cached): any => {
              return _cE("view", _uM({
                key: 'row_' + r,
                class: "boardRow",
                style: _nS(rowStyle.value)
              }), [
                _cE(Fragment, null, RenderHelpers.renderList(cols, (c, __key, __index, _cached): any => {
                  return _cE("view", _uM({
                    key: r + '_' + c,
                    class: _nC(["cell", cellClass(r, c)]),
                    style: _nS(cellStyle.value),
                    onClick: () => {onTapCell(r, c)}
                  }), [
                    _cE("text", _uM({ class: "piece" }), _tD(pieceAt(r, c)), 1 /* TEXT */)
                  ], 14 /* CLASS, STYLE, PROPS */, ["onClick"])
                }), 64 /* STABLE_FRAGMENT */)
              ], 4 /* STYLE */)
            }), 64 /* STABLE_FRAGMENT */)
          ], 4 /* STYLE */)
        ]),
        _cE("view", _uM({
          class: "filesRow",
          style: _nS(filesRowStyle.value)
        }), [
          _cE("view", _uM({
            class: "filesSpacer",
            style: _nS(filesSpacerStyle.value)
          }), null, 4 /* STYLE */),
          _cE(Fragment, null, RenderHelpers.renderList(cols, (c, __key, __index, _cached): any => {
            return _cE("view", _uM({
              key: 'file_' + c,
              class: "fileCell",
              style: _nS(fileCellStyle.value)
            }), [
              _cE("text", _uM({ class: "coordText" }), _tD(fileLabel(c)), 1 /* TEXT */)
            ], 4 /* STYLE */)
          }), 64 /* STABLE_FRAGMENT */)
        ], 4 /* STYLE */)
      ], 4 /* STYLE */)
    ]),
    _cE("view", _uM({ class: "panel" }), [
      _cE("view", _uM({ class: "rowInfo rowFirst" }), [
        _cE("text", _uM({ class: "label" }), "当前回合"),
        _cE("text", _uM({ class: "value" }), _tD(turnText.value), 1 /* TEXT */)
      ]),
      _cE("view", _uM({ class: "rowInfo" }), [
        _cE("text", _uM({ class: "label" }), "选中"),
        _cE("text", _uM({ class: "value" }), _tD(selectedText.value), 1 /* TEXT */)
      ]),
      _cE("view", _uM({ class: "btns" }), [
        _cE("view", _uM({
          class: "btn",
          onClick: resetBoard
        }), [
          _cE("text", _uM({ class: "btnText" }), "重置棋盘")
        ])
      ])
    ])
  ])
}
}

})
export default __sfc__
const GenPagesGameGameStyles = [_uM([["page", _pS(_uM([["paddingTop", 18], ["paddingRight", 18], ["paddingBottom", 18], ["paddingLeft", 18], ["display", "flex"], ["flexDirection", "column"]]))], ["head", _pS(_uM([["marginBottom", 12]]))], ["h1", _pS(_uM([["fontSize", 20], ["fontWeight", "700"]]))], ["sub", _pS(_uM([["fontSize", 12], ["opacity", 0.6], ["marginTop", 4]]))], ["boardWrap", _pS(_uM([["width", "100%"], ["display", "flex"], ["justifyContent", "center"], ["marginBottom", 14]]))], ["boardFrame", _pS(_uM([["display", "flex"], ["flexDirection", "column"], ["borderTopLeftRadius", 0], ["borderTopRightRadius", 0], ["borderBottomRightRadius", 0], ["borderBottomLeftRadius", 0], ["overflow", "hidden"], ["borderTopWidth", "medium"], ["borderRightWidth", "medium"], ["borderBottomWidth", "medium"], ["borderLeftWidth", "medium"], ["borderTopStyle", "none"], ["borderRightStyle", "none"], ["borderBottomStyle", "none"], ["borderLeftStyle", "none"], ["borderTopColor", "#000000"], ["borderRightColor", "#000000"], ["borderBottomColor", "#000000"], ["borderLeftColor", "#000000"], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0)"], ["boxSizing", "border-box"]]))], ["boardRowWrap", _pS(_uM([["display", "flex"], ["flexDirection", "row"]]))], ["ranksCol", _pS(_uM([["display", "flex"], ["flexDirection", "column"], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0)"], ["boxSizing", "border-box"]]))], ["rankCell", _pS(_uM([["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"], ["boxSizing", "border-box"]]))], ["filesRow", _pS(_uM([["display", "flex"], ["flexDirection", "row"], ["alignItems", "center"], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0)"], ["boxSizing", "border-box"]]))], ["filesSpacer", _pS(_uM([["flexShrink", 0], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0)"]]))], ["fileCell", _pS(_uM([["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"], ["boxSizing", "border-box"]]))], ["coordText", _pS(_uM([["fontSize", 12], ["opacity", 0.6], ["fontWeight", "700"]]))], ["board", _pS(_uM([["overflow", "hidden"], ["boxSizing", "border-box"], ["display", "flex"], ["flexDirection", "column"]]))], ["boardRow", _pS(_uM([["display", "flex"], ["flexDirection", "row"]]))], ["cell", _pS(_uM([["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"], ["boxSizing", "border-box"], ["flexShrink", 0]]))], ["light", _pS(_uM([["backgroundImage", "none"], ["backgroundColor", "#f0d9b5"]]))], ["dark", _pS(_uM([["backgroundImage", "none"], ["backgroundColor", "#b58863"]]))], ["selected", _pS(_uM([["borderTopWidth", 3], ["borderRightWidth", 3], ["borderBottomWidth", 3], ["borderLeftWidth", 3], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(31,111,235,0.9)"], ["borderRightColor", "rgba(31,111,235,0.9)"], ["borderBottomColor", "rgba(31,111,235,0.9)"], ["borderLeftColor", "rgba(31,111,235,0.9)"], ["boxShadow", "inset 0 0 0 1px rgba(255,255,255,0.25)"]]))], ["piece", _pS(_uM([["fontSize", 26], ["lineHeight", "26px"]]))], ["panel", _pS(_uM([["paddingTop", 14], ["paddingRight", 14], ["paddingBottom", 14], ["paddingLeft", 14], ["borderTopLeftRadius", 14], ["borderTopRightRadius", 14], ["borderBottomRightRadius", 14], ["borderBottomLeftRadius", 14], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.08)"], ["borderRightColor", "rgba(0,0,0,0.08)"], ["borderBottomColor", "rgba(0,0,0,0.08)"], ["borderLeftColor", "rgba(0,0,0,0.08)"], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0.02)"]]))], ["rowInfo", _pS(_uM([["display", "flex"], ["alignItems", "center"], ["justifyContent", "space-between"], ["paddingTop", 8], ["paddingRight", 0], ["paddingBottom", 8], ["paddingLeft", 0], ["borderTopWidth", 1], ["borderTopStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.06)"]]))], ["rowFirst", _pS(_uM([["borderTopWidth", "medium"], ["borderTopStyle", "none"], ["borderTopColor", "#000000"], ["paddingTop", 0]]))], ["label", _pS(_uM([["fontSize", 12], ["opacity", 0.6]]))], ["value", _pS(_uM([["fontSize", 14], ["fontWeight", "700"]]))], ["btns", _pS(_uM([["marginTop", 12]]))], ["btn", _pS(_uM([["height", 42], ["borderTopLeftRadius", 12], ["borderTopRightRadius", 12], ["borderBottomRightRadius", 12], ["borderBottomLeftRadius", 12], ["backgroundImage", "none"], ["backgroundColor", "rgba(31,111,235,0.12)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(31,111,235,0.25)"], ["borderRightColor", "rgba(31,111,235,0.25)"], ["borderBottomColor", "rgba(31,111,235,0.25)"], ["borderLeftColor", "rgba(31,111,235,0.25)"], ["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"]]))], ["btnText", _pS(_uM([["fontSize", 14], ["fontWeight", "700"]]))]])]
