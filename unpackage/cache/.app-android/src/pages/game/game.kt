@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNIC4B6EBD
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlin.properties.Delegates
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
import io.dcloud.uniapp.extapi.showActionSheet as uni_showActionSheet
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesGameGame : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesGameGame) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesGameGame
            val _cache = __ins.renderCache
            val rows: UTSArray<Number> = _uA(
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7
            )
            val cols: UTSArray<Number> = _uA(
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7
            )
            val boardPx = ref<Number>(320)
            val coordPx = ref<Number>(22)
            fun gen_clamp_fn(n: Number, minV: Number, maxV: Number): Number {
                if (n < minV) {
                    return minV
                }
                if (n > maxV) {
                    return maxV
                }
                return n
            }
            val clamp = ::gen_clamp_fn
            fun gen_calcCoordFromCell_fn(cell: Number): Number {
                var v = Math.floor(cell * 0.55)
                if (v < 18) {
                    v = 18
                }
                if (v > 26) {
                    v = 26
                }
                return v
            }
            val calcCoordFromCell = ::gen_calcCoordFromCell_fn
            fun gen_recalcSizes_fn() {
                val info: GetSystemInfoResult = uni_getSystemInfoSync()
                val ww: Number = info.windowWidth
                val maxTotal = ww - 36
                var c: Number = 22
                var b = maxTotal - c
                run {
                    var i: Number = 0
                    while(i < 3){
                        b = clamp(b, 240, 360)
                        b = b - (b % 8)
                        val cell = b / 8
                        c = calcCoordFromCell(cell)
                        b = maxTotal - c
                        i++
                    }
                }
                b = clamp(b, 240, 360)
                b = b - (b % 8)
                c = calcCoordFromCell(b / 8)
                boardPx.value = b
                coordPx.value = c
            }
            val recalcSizes = ::gen_recalcSizes_fn
            recalcSizes()
            val cellPx = computed(fun(): Number {
                return boardPx.value / 8
            }
            )
            val boardStyle = computed(fun(): String {
                val px = boardPx.value.toString(10) + "px"
                return "width:" + px + ";height:" + px + ";"
            }
            )
            val rowStyle = computed(fun(): String {
                val w = boardPx.value.toString(10) + "px"
                val h = cellPx.value.toString(10) + "px"
                return "width:" + w + ";height:" + h + ";"
            }
            )
            val cellStyle = computed(fun(): String {
                val px = cellPx.value.toString(10) + "px"
                return "width:" + px + ";height:" + px + ";flex:0 0 " + px + ";"
            }
            )
            val frameStyle = computed(fun(): String {
                val w = (boardPx.value + coordPx.value).toString(10) + "px"
                val h = (boardPx.value + coordPx.value).toString(10) + "px"
                return "width:" + w + ";height:" + h + ";"
            }
            )
            val ranksColStyle = computed(fun(): String {
                val w = coordPx.value.toString(10) + "px"
                val h = boardPx.value.toString(10) + "px"
                return "width:" + w + ";height:" + h + ";"
            }
            )
            val rankCellStyle = computed(fun(): String {
                val w = coordPx.value.toString(10) + "px"
                val h = cellPx.value.toString(10) + "px"
                return "width:" + w + ";height:" + h + ";"
            }
            )
            val filesRowStyle = computed(fun(): String {
                val w = (boardPx.value + coordPx.value).toString(10) + "px"
                val h = coordPx.value.toString(10) + "px"
                return "width:" + w + ";height:" + h + ";"
            }
            )
            val filesSpacerStyle = computed(fun(): String {
                val w = coordPx.value.toString(10) + "px"
                val h = coordPx.value.toString(10) + "px"
                return "width:" + w + ";height:" + h + ";"
            }
            )
            val fileCellStyle = computed(fun(): String {
                val w = cellPx.value.toString(10) + "px"
                val h = coordPx.value.toString(10) + "px"
                return "width:" + w + ";height:" + h + ";"
            }
            )
            fun gen_rankLabel_fn(r: Number): String {
                return (8 - r).toString(10)
            }
            val rankLabel = ::gen_rankLabel_fn
            fun gen_fileLabel_fn(c: Number): String {
                return String.fromCharCode(97 + c)
            }
            val fileLabel = ::gen_fileLabel_fn
            val turn = ref<Turn>("white")
            open class Pos {
                open lateinit var r: Number
                open lateinit var c: Number
                constructor(r: Number, c: Number){
                    this.r = r
                    this.c = c
                }
            }
            val selected = ref<Pos?>(null)
            val BR = "♜"
            val BN = "♞"
            val BB = "♝"
            val BQ = "♛"
            val BK = "♚"
            val BP = "♟"
            val WR = "♖"
            val WN = "♘"
            val WB = "♗"
            val WQ = "♕"
            val WK = "♔"
            val WP = "♙"
            fun gen_createInitBoard_fn(): Board {
                return _uA(
                    _uA(
                        BR,
                        BN,
                        BB,
                        BQ,
                        BK,
                        BB,
                        BN,
                        BR
                    ),
                    _uA(
                        BP,
                        BP,
                        BP,
                        BP,
                        BP,
                        BP,
                        BP,
                        BP
                    ),
                    _uA(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    ),
                    _uA(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    ),
                    _uA(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    ),
                    _uA(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    ),
                    _uA(
                        WP,
                        WP,
                        WP,
                        WP,
                        WP,
                        WP,
                        WP,
                        WP
                    ),
                    _uA(
                        WR,
                        WN,
                        WB,
                        WQ,
                        WK,
                        WB,
                        WN,
                        WR
                    )
                )
            }
            val createInitBoard = ::gen_createInitBoard_fn
            val board = ref<Board>(createInitBoard())
            fun gen_pieceAt_fn(r: Number, c: Number): String {
                val p = board.value[r][c]
                return if (p == null) {
                    ""
                } else {
                    p
                }
            }
            val pieceAt = ::gen_pieceAt_fn
            fun gen_isWhitePiece_fn(p: String): Boolean {
                return p == WR || p == WN || p == WB || p == WQ || p == WK || p == WP
            }
            val isWhitePiece = ::gen_isWhitePiece_fn
            fun gen_isBlackPiece_fn(p: String): Boolean {
                return p == BR || p == BN || p == BB || p == BQ || p == BK || p == BP
            }
            val isBlackPiece = ::gen_isBlackPiece_fn
            fun gen_colorOf_fn(p: String): Turn {
                return if (isWhitePiece(p)) {
                    "white"
                } else {
                    "black"
                }
            }
            val colorOf = ::gen_colorOf_fn
            fun gen_inBounds_fn(r: Number, c: Number): Boolean {
                return r >= 0 && r < 8 && c >= 0 && c < 8
            }
            val inBounds = ::gen_inBounds_fn
            fun gen_cloneBoard_fn(b: Board): Board {
                val nb: Board = _uA()
                run {
                    var i: Number = 0
                    while(i < 8){
                        val nr: UTSArray<String?> = _uA()
                        run {
                            var j: Number = 0
                            while(j < 8){
                                nr.push(b[i][j])
                                j++
                            }
                        }
                        nb.push(nr)
                        i++
                    }
                }
                return nb
            }
            val cloneBoard = ::gen_cloneBoard_fn
            fun gen_isEmpty_fn(b: Board, r: Number, c: Number): Boolean {
                return b[r][c] == null
            }
            val isEmpty = ::gen_isEmpty_fn
            fun gen_isEnemyAt_fn(b: Board, r: Number, c: Number, me: Turn): Boolean {
                val p = b[r][c]
                if (p == null) {
                    return false
                }
                return if ((me == "white")) {
                    isBlackPiece(p)
                } else {
                    isWhitePiece(p)
                }
            }
            val isEnemyAt = ::gen_isEnemyAt_fn
            fun gen_rayClear_fn(b: Board, sr: Number, sc: Number, dr: Number, dc: Number): Boolean {
                val dR = dr - sr
                val dC = dc - sc
                val stepR = if (dR == 0) {
                    0
                } else {
                    if (dR > 0) {
                        1
                    } else {
                        -1
                    }
                }
                val stepC = if (dC == 0) {
                    0
                } else {
                    if (dC > 0) {
                        1
                    } else {
                        -1
                    }
                }
                var r = sr + stepR
                var c = sc + stepC
                while(r != dr || c != dc){
                    if (!isEmpty(b, r, c)) {
                        return false
                    }
                    r += stepR
                    c += stepC
                }
                return true
            }
            val rayClear = ::gen_rayClear_fn
            fun gen_pieceType_fn(p: String): String {
                if (p == WP || p == BP) {
                    return "P"
                }
                if (p == WN || p == BN) {
                    return "N"
                }
                if (p == WB || p == BB) {
                    return "B"
                }
                if (p == WR || p == BR) {
                    return "R"
                }
                if (p == WQ || p == BQ) {
                    return "Q"
                }
                return "K"
            }
            val pieceType = ::gen_pieceType_fn
            fun gen_findKing_fn(b: Board, who: Turn): Pos? {
                val k = if ((who == "white")) {
                    WK
                } else {
                    BK
                }
                run {
                    var r: Number = 0
                    while(r < 8){
                        run {
                            var c: Number = 0
                            while(c < 8){
                                if (b[r][c] == k) {
                                    return Pos(r, c)
                                }
                                c++
                            }
                        }
                        r++
                    }
                }
                return null
            }
            val findKing = ::gen_findKing_fn
            fun gen_isSquareAttacked_fn(b: Board, tr: Number, tc: Number, by__1: Turn): Boolean {
                if (by__1 == "white") {
                    val r = tr + 1
                    if (inBounds(r, tc - 1) && b[r][tc - 1] == WP) {
                        return true
                    }
                    if (inBounds(r, tc + 1) && b[r][tc + 1] == WP) {
                        return true
                    }
                } else {
                    val r = tr - 1
                    if (inBounds(r, tc - 1) && b[r][tc - 1] == BP) {
                        return true
                    }
                    if (inBounds(r, tc + 1) && b[r][tc + 1] == BP) {
                        return true
                    }
                }
                val kd = _uA(
                    _uA(
                        -2,
                        -1
                    ),
                    _uA(
                        -2,
                        1
                    ),
                    _uA(
                        -1,
                        -2
                    ),
                    _uA(
                        -1,
                        2
                    ),
                    _uA(
                        1,
                        -2
                    ),
                    _uA(
                        1,
                        2
                    ),
                    _uA(
                        2,
                        -1
                    ),
                    _uA(
                        2,
                        1
                    )
                )
                run {
                    var i: Number = 0
                    while(i < kd.length){
                        val rr = tr + kd[i][0]
                        val cc = tc + kd[i][1]
                        if (!inBounds(rr, cc)) {
                            i++
                            continue
                        }
                        val p = b[rr][cc]
                        if (p == null) {
                            i++
                            continue
                        }
                        if (by__1 == "white" && p == WN) {
                            return true
                        }
                        if (by__1 == "black" && p == BN) {
                            return true
                        }
                        i++
                    }
                }
                run {
                    var rr = tr - 1
                    while(rr <= tr + 1){
                        run {
                            var cc = tc - 1
                            while(cc <= tc + 1){
                                if (rr == tr && cc == tc) {
                                    cc++
                                    continue
                                }
                                if (!inBounds(rr, cc)) {
                                    cc++
                                    continue
                                }
                                val p = b[rr][cc]
                                if (p == null) {
                                    cc++
                                    continue
                                }
                                if (by__1 == "white" && p == WK) {
                                    return true
                                }
                                if (by__1 == "black" && p == BK) {
                                    return true
                                }
                                cc++
                            }
                        }
                        rr++
                    }
                }
                val rd = _uA(
                    _uA(
                        -1,
                        0
                    ),
                    _uA(
                        1,
                        0
                    ),
                    _uA(
                        0,
                        -1
                    ),
                    _uA(
                        0,
                        1
                    )
                )
                run {
                    var i: Number = 0
                    while(i < rd.length){
                        var rr = tr + rd[i][0]
                        var cc = tc + rd[i][1]
                        while(inBounds(rr, cc)){
                            val p = b[rr][cc]
                            if (p != null) {
                                if (by__1 == "white" && (p == WR || p == WQ)) {
                                    return true
                                }
                                if (by__1 == "black" && (p == BR || p == BQ)) {
                                    return true
                                }
                                break
                            }
                            rr += rd[i][0]
                            cc += rd[i][1]
                        }
                        i++
                    }
                }
                val bd = _uA(
                    _uA(
                        -1,
                        -1
                    ),
                    _uA(
                        -1,
                        1
                    ),
                    _uA(
                        1,
                        -1
                    ),
                    _uA(
                        1,
                        1
                    )
                )
                run {
                    var i: Number = 0
                    while(i < bd.length){
                        var rr = tr + bd[i][0]
                        var cc = tc + bd[i][1]
                        while(inBounds(rr, cc)){
                            val p = b[rr][cc]
                            if (p != null) {
                                if (by__1 == "white" && (p == WB || p == WQ)) {
                                    return true
                                }
                                if (by__1 == "black" && (p == BB || p == BQ)) {
                                    return true
                                }
                                break
                            }
                            rr += bd[i][0]
                            cc += bd[i][1]
                        }
                        i++
                    }
                }
                return false
            }
            val isSquareAttacked = ::gen_isSquareAttacked_fn
            fun gen_isKingInCheck_fn(b: Board, who: Turn): Boolean {
                val k = findKing(b, who)
                if (k == null) {
                    return false
                }
                val opp: Turn = if ((who == "white")) {
                    "black"
                } else {
                    "white"
                }
                return isSquareAttacked(b, k.r, k.c, opp)
            }
            val isKingInCheck = ::gen_isKingInCheck_fn
            fun gen_canMovePieceRaw_fn(b: Board, sr: Number, sc: Number, dr: Number, dc: Number, who: Turn): Boolean {
                if (!inBounds(sr, sc) || !inBounds(dr, dc)) {
                    return false
                }
                if (sr == dr && sc == dc) {
                    return false
                }
                val p = b[sr][sc]
                if (p == null) {
                    return false
                }
                if (colorOf(p) != who) {
                    return false
                }
                val dst = b[dr][dc]
                if (dst != null && colorOf(dst) == who) {
                    return false
                }
                val t = pieceType(p)
                val dR = dr - sr
                val dC = dc - sc
                val absR = Math.abs(dR)
                val absC = Math.abs(dC)
                if (t == "P") {
                    val dir = if ((who == "white")) {
                        -1
                    } else {
                        1
                    }
                    val startRow = if ((who == "white")) {
                        6
                    } else {
                        1
                    }
                    if (dC == 0) {
                        if (dR == dir && isEmpty(b, dr, dc)) {
                            return true
                        }
                        if (sr == startRow && dR == 2 * dir) {
                            val mid = sr + dir
                            if (isEmpty(b, mid, sc) && isEmpty(b, dr, dc)) {
                                return true
                            }
                        }
                        return false
                    }
                    if (absC == 1 && dR == dir) {
                        return isEnemyAt(b, dr, dc, who)
                    }
                    return false
                }
                if (t == "N") {
                    return (absR == 2 && absC == 1) || (absR == 1 && absC == 2)
                }
                if (t == "B") {
                    if (absR != absC) {
                        return false
                    }
                    return rayClear(b, sr, sc, dr, dc)
                }
                if (t == "R") {
                    if (!(dR == 0 || dC == 0)) {
                        return false
                    }
                    return rayClear(b, sr, sc, dr, dc)
                }
                if (t == "Q") {
                    val ok = (absR == absC) || (dR == 0 || dC == 0)
                    if (!ok) {
                        return false
                    }
                    return rayClear(b, sr, sc, dr, dc)
                }
                return absR <= 1 && absC <= 1
            }
            val canMovePieceRaw = ::gen_canMovePieceRaw_fn
            fun gen_isLegalMove_fn(b: Board, sr: Number, sc: Number, dr: Number, dc: Number, who: Turn): Boolean {
                if (!canMovePieceRaw(b, sr, sc, dr, dc, who)) {
                    return false
                }
                val nb = cloneBoard(b)
                nb[dr][dc] = nb[sr][sc]
                nb[sr][sc] = null
                if (isKingInCheck(nb, who)) {
                    return false
                }
                return true
            }
            val isLegalMove = ::gen_isLegalMove_fn
            fun gen_hasAnyLegalMove_fn(b: Board, who: Turn): Boolean {
                run {
                    var sr: Number = 0
                    while(sr < 8){
                        run {
                            var sc: Number = 0
                            while(sc < 8){
                                val p = b[sr][sc]
                                if (p == null) {
                                    sc++
                                    continue
                                }
                                if (colorOf(p) !== who) {
                                    sc++
                                    continue
                                }
                                run {
                                    var dr: Number = 0
                                    while(dr < 8){
                                        run {
                                            var dc: Number = 0
                                            while(dc < 8){
                                                if (isLegalMove(b, sr, sc, dr, dc, who)) {
                                                    return true
                                                }
                                                dc++
                                            }
                                        }
                                        dr++
                                    }
                                }
                                sc++
                            }
                        }
                        sr++
                    }
                }
                return false
            }
            val hasAnyLegalMove = ::gen_hasAnyLegalMove_fn
            fun gen_getEndStateForSideToMove_fn(b: Board, sideToMove: Turn): EndState {
                val hasMove = hasAnyLegalMove(b, sideToMove)
                if (hasMove) {
                    return "playing"
                }
                val inCheck = isKingInCheck(b, sideToMove)
                return if (inCheck) {
                    "checkmate"
                } else {
                    "stalemate"
                }
            }
            val getEndStateForSideToMove = ::gen_getEndStateForSideToMove_fn
            fun gen_opposite_fn(who: Turn): Turn {
                return if (who == "white") {
                    "black"
                } else {
                    "white"
                }
            }
            val opposite = ::gen_opposite_fn
            fun gen_promotionPiece_fn(who: Turn, choice: Number): String {
                if (who == "white") {
                    if (choice == 1) {
                        return WR
                    }
                    if (choice == 2) {
                        return WB
                    }
                    if (choice == 3) {
                        return WN
                    }
                    return WQ
                } else {
                    if (choice == 1) {
                        return BR
                    }
                    if (choice == 2) {
                        return BB
                    }
                    if (choice == 3) {
                        return BN
                    }
                    return BQ
                }
            }
            val promotionPiece = ::gen_promotionPiece_fn
            fun gen_shouldPromote_fn(p: String, dr: Number): Boolean {
                if (p == WP && dr == 0) {
                    return true
                }
                if (p == BP && dr == 7) {
                    return true
                }
                return false
            }
            val shouldPromote = ::gen_shouldPromote_fn
            fun gen_askPromotion_fn(who: Turn): UTSPromise<Number> {
                return UTSPromise(fun(resolve, _reject){
                    uni_showActionSheet(ShowActionSheetOptions(itemList = _uA(
                        "升后 ♕/♛",
                        "升车 ♖/♜",
                        "升象 ♗/♝",
                        "升马 ♘/♞"
                    ), success = fun(res: ShowActionSheetSuccess){
                        val idx: Number = res.tapIndex
                        if (idx >= 0 && idx <= 3) {
                            resolve(idx)
                        } else {
                            resolve(0)
                        }
                    }
                    , fail = fun(_){
                        resolve(0)
                    }
                    ))
                }
                )
            }
            val askPromotion = ::gen_askPromotion_fn
            fun gen_promoteIfNeeded_fn(b: Board, dr: Number, dc: Number, movedPiece: String, whoMoved: Turn): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (!shouldPromote(movedPiece, dr)) {
                            return@w1
                        }
                        val choice = await(askPromotion(whoMoved))
                        b[dr][dc] = promotionPiece(whoMoved, choice)
                })
            }
            val promoteIfNeeded = ::gen_promoteIfNeeded_fn
            fun gen_resetBoard_fn() {
                board.value = createInitBoard()
                selected.value = null
                turn.value = "white"
            }
            val resetBoard = ::gen_resetBoard_fn
            fun gen_onTapCell_fn(r: Number, c: Number): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        val piece = board.value[r][c]
                        if (selected.value == null) {
                            if (piece == null) {
                                return@w1
                            }
                            if (turn.value == "white" && isWhitePiece(piece)) {
                                selected.value = Pos(r, c)
                            } else if (turn.value == "black" && isBlackPiece(piece)) {
                                selected.value = Pos(r, c)
                            }
                            return@w1
                        }
                        val sr = selected.value!!.r
                        val sc = selected.value!!.c
                        val moving = board.value[sr][sc]
                        if (moving == null) {
                            selected.value = null
                            return@w1
                        }
                        if (piece != null && ((turn.value == "white" && isWhitePiece(piece)) || (turn.value == "black" && isBlackPiece(piece)))) {
                            selected.value = Pos(r, c)
                            return@w1
                        }
                        val ok = isLegalMove(board.value, sr, sc, r, c, turn.value)
                        if (!ok) {
                            uni_showToast(ShowToastOptions(title = "非法走法", icon = "none"))
                            return@w1
                        }
                        val whoMoved: Turn = turn.value
                        board.value[r][c] = moving
                        board.value[sr][sc] = null
                        selected.value = null
                        await(promoteIfNeeded(board.value, r, c, moving, whoMoved))
                        val nextTurn: Turn = opposite(whoMoved)
                        turn.value = nextTurn
                        val endState = getEndStateForSideToMove(board.value, nextTurn)
                        if (endState != "playing") {
                            var msg = ""
                            if (endState == "checkmate") {
                                msg = if ((whoMoved == "white")) {
                                    "白方胜利（将死）！"
                                } else {
                                    "黑方胜利（将死）！"
                                }
                            } else {
                                msg = "和棋（无合法走法）🤝"
                            }
                            uni_showModal(ShowModalOptions(title = "对局结束", content = msg, showCancel = false, success = fun(_){
                                resetBoard()
                            }
                            ))
                        }
                })
            }
            val onTapCell = ::gen_onTapCell_fn
            fun gen_cellClass_fn(r: Number, c: Number): String {
                var cls = if (((r + c) % 2 == 1)) {
                    "dark"
                } else {
                    "light"
                }
                if (selected.value != null && selected.value!!.r == r && selected.value!!.c == c) {
                    cls = cls + " selected"
                }
                return cls
            }
            val cellClass = ::gen_cellClass_fn
            val selectedText = computed(fun(): String {
                if (selected.value == null) {
                    return "无"
                }
                val file = String.fromCharCode(97 + selected.value!!.c)
                val rank = (8 - selected.value!!.r).toString(10)
                return file + rank
            }
            )
            val turnText = computed(fun(): String {
                return if (turn.value == "white") {
                    "白方回合"
                } else {
                    "黑方回合"
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "page"), _uA(
                    _cE("view", _uM("class" to "head"), _uA(
                        _cE("text", _uM("class" to "h1"), "Game"),
                        _cE("text", _uM("class" to "sub"), _tD(turnText.value), 1)
                    )),
                    _cE("view", _uM("class" to "boardWrap"), _uA(
                        _cE("view", _uM("class" to "boardFrame", "style" to _nS(frameStyle.value)), _uA(
                            _cE("view", _uM("class" to "boardRowWrap"), _uA(
                                _cE("view", _uM("class" to "ranksCol", "style" to _nS(ranksColStyle.value)), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(rows, fun(r, __key, __index, _cached): Any {
                                        return _cE("view", _uM("key" to ("rank_" + r), "class" to "rankCell", "style" to _nS(rankCellStyle.value)), _uA(
                                            _cE("text", _uM("class" to "coordText"), _tD(rankLabel(r)), 1)
                                        ), 4)
                                    }
                                    ), 64)
                                ), 4),
                                _cE("view", _uM("class" to "board", "style" to _nS(boardStyle.value)), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(rows, fun(r, __key, __index, _cached): Any {
                                        return _cE("view", _uM("key" to ("row_" + r), "class" to "boardRow", "style" to _nS(rowStyle.value)), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(cols, fun(c, __key, __index, _cached): Any {
                                                return _cE("view", _uM("key" to (r + "_" + c), "class" to _nC(_uA(
                                                    "cell",
                                                    cellClass(r, c)
                                                )), "style" to _nS(cellStyle.value), "onClick" to fun(){
                                                    onTapCell(r, c)
                                                }
                                                ), _uA(
                                                    _cE("text", _uM("class" to "piece"), _tD(pieceAt(r, c)), 1)
                                                ), 14, _uA(
                                                    "onClick"
                                                ))
                                            }
                                            ), 64)
                                        ), 4)
                                    }
                                    ), 64)
                                ), 4)
                            )),
                            _cE("view", _uM("class" to "filesRow", "style" to _nS(filesRowStyle.value)), _uA(
                                _cE("view", _uM("class" to "filesSpacer", "style" to _nS(filesSpacerStyle.value)), null, 4),
                                _cE(Fragment, null, RenderHelpers.renderList(cols, fun(c, __key, __index, _cached): Any {
                                    return _cE("view", _uM("key" to ("file_" + c), "class" to "fileCell", "style" to _nS(fileCellStyle.value)), _uA(
                                        _cE("text", _uM("class" to "coordText"), _tD(fileLabel(c)), 1)
                                    ), 4)
                                }
                                ), 64)
                            ), 4)
                        ), 4)
                    )),
                    _cE("view", _uM("class" to "panel"), _uA(
                        _cE("view", _uM("class" to "rowInfo rowFirst"), _uA(
                            _cE("text", _uM("class" to "label"), "当前回合"),
                            _cE("text", _uM("class" to "value"), _tD(turnText.value), 1)
                        )),
                        _cE("view", _uM("class" to "rowInfo"), _uA(
                            _cE("text", _uM("class" to "label"), "选中"),
                            _cE("text", _uM("class" to "value"), _tD(selectedText.value), 1)
                        )),
                        _cE("view", _uM("class" to "btns"), _uA(
                            _cE("view", _uM("class" to "btn", "onClick" to resetBoard), _uA(
                                _cE("text", _uM("class" to "btnText"), "重置棋盘")
                            ))
                        ))
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ), _uA(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("page" to _pS(_uM("paddingTop" to 18, "paddingRight" to 18, "paddingBottom" to 18, "paddingLeft" to 18, "display" to "flex", "flexDirection" to "column")), "head" to _pS(_uM("marginBottom" to 12)), "h1" to _pS(_uM("fontSize" to 20, "fontWeight" to "700")), "sub" to _pS(_uM("fontSize" to 12, "opacity" to 0.6, "marginTop" to 4)), "boardWrap" to _pS(_uM("width" to "100%", "display" to "flex", "justifyContent" to "center", "marginBottom" to 14)), "boardFrame" to _pS(_uM("display" to "flex", "flexDirection" to "column", "borderTopLeftRadius" to 0, "borderTopRightRadius" to 0, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0, "overflow" to "hidden", "borderTopWidth" to "medium", "borderRightWidth" to "medium", "borderBottomWidth" to "medium", "borderLeftWidth" to "medium", "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0)", "boxSizing" to "border-box")), "boardRowWrap" to _pS(_uM("display" to "flex", "flexDirection" to "row")), "ranksCol" to _pS(_uM("display" to "flex", "flexDirection" to "column", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0)", "boxSizing" to "border-box")), "rankCell" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "center", "boxSizing" to "border-box")), "filesRow" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0)", "boxSizing" to "border-box")), "filesSpacer" to _pS(_uM("flexShrink" to 0, "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0)")), "fileCell" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "center", "boxSizing" to "border-box")), "coordText" to _pS(_uM("fontSize" to 12, "opacity" to 0.6, "fontWeight" to "700")), "board" to _pS(_uM("overflow" to "hidden", "boxSizing" to "border-box", "display" to "flex", "flexDirection" to "column")), "boardRow" to _pS(_uM("display" to "flex", "flexDirection" to "row")), "cell" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "center", "boxSizing" to "border-box", "flexShrink" to 0)), "light" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "#f0d9b5")), "dark" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "#b58863")), "selected" to _pS(_uM("borderTopWidth" to 3, "borderRightWidth" to 3, "borderBottomWidth" to 3, "borderLeftWidth" to 3, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(31,111,235,0.9)", "borderRightColor" to "rgba(31,111,235,0.9)", "borderBottomColor" to "rgba(31,111,235,0.9)", "borderLeftColor" to "rgba(31,111,235,0.9)", "boxShadow" to "inset 0 0 0 1px rgba(255,255,255,0.25)")), "piece" to _pS(_uM("fontSize" to 26, "lineHeight" to "26px")), "panel" to _pS(_uM("paddingTop" to 14, "paddingRight" to 14, "paddingBottom" to 14, "paddingLeft" to 14, "borderTopLeftRadius" to 14, "borderTopRightRadius" to 14, "borderBottomRightRadius" to 14, "borderBottomLeftRadius" to 14, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.08)", "borderRightColor" to "rgba(0,0,0,0.08)", "borderBottomColor" to "rgba(0,0,0,0.08)", "borderLeftColor" to "rgba(0,0,0,0.08)", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.02)")), "rowInfo" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to 8, "paddingRight" to 0, "paddingBottom" to 8, "paddingLeft" to 0, "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.06)")), "rowFirst" to _pS(_uM("borderTopWidth" to "medium", "borderTopStyle" to "none", "borderTopColor" to "#000000", "paddingTop" to 0)), "label" to _pS(_uM("fontSize" to 12, "opacity" to 0.6)), "value" to _pS(_uM("fontSize" to 14, "fontWeight" to "700")), "btns" to _pS(_uM("marginTop" to 12)), "btn" to _pS(_uM("height" to 42, "borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "borderBottomRightRadius" to 12, "borderBottomLeftRadius" to 12, "backgroundImage" to "none", "backgroundColor" to "rgba(31,111,235,0.12)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(31,111,235,0.25)", "borderRightColor" to "rgba(31,111,235,0.25)", "borderBottomColor" to "rgba(31,111,235,0.25)", "borderLeftColor" to "rgba(31,111,235,0.25)", "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "btnText" to _pS(_uM("fontSize" to 14, "fontWeight" to "700")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
