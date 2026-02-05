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
            fun gen_calcBoardPx_fn(): Number {
                val info: GetSystemInfoResult = uni_getSystemInfoSync()
                var ww: Number = info.windowWidth
                var px = ww - 36
                px = clamp(px, 240, 360)
                px = px - (px % 8)
                return px
            }
            val calcBoardPx = ::gen_calcBoardPx_fn
            boardPx.value = calcBoardPx()
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
            fun gen_onTapCell_fn(r: Number, c: Number) {
                val piece = board.value[r][c]
                if (selected.value == null) {
                    if (piece == null) {
                        return
                    }
                    if (turn.value == "white" && isWhitePiece(piece)) {
                        selected.value = Pos(r, c)
                    } else if (turn.value == "black" && isBlackPiece(piece)) {
                        selected.value = Pos(r, c)
                    }
                    return
                }
                val sr = selected.value!!.r
                val sc = selected.value!!.c
                val moving = board.value[sr][sc]
                if (moving == null) {
                    selected.value = null
                    return
                }
                if (piece != null && ((turn.value == "white" && isWhitePiece(piece)) || (turn.value == "black" && isBlackPiece(piece)))) {
                    selected.value = Pos(r, c)
                    return
                }
                board.value[r][c] = moving
                board.value[sr][sc] = null
                selected.value = null
                turn.value = if ((turn.value == "white")) {
                    "black"
                } else {
                    "white"
                }
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
            fun gen_resetBoard_fn() {
                board.value = createInitBoard()
                selected.value = null
                turn.value = "white"
            }
            val resetBoard = ::gen_resetBoard_fn
            return fun(): Any? {
                return _cE("view", _uM("class" to "page"), _uA(
                    _cE("view", _uM("class" to "head"), _uA(
                        _cE("text", _uM("class" to "h1"), "Game"),
                        _cE("text", _uM("class" to "sub"), _tD(turnText.value), 1)
                    )),
                    _cE("view", _uM("class" to "boardWrap"), _uA(
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
                return _uM("page" to _pS(_uM("paddingTop" to 18, "paddingRight" to 18, "paddingBottom" to 18, "paddingLeft" to 18, "display" to "flex", "flexDirection" to "column")), "head" to _pS(_uM("marginBottom" to 12)), "h1" to _pS(_uM("fontSize" to 20, "fontWeight" to "700")), "sub" to _pS(_uM("fontSize" to 12, "opacity" to 0.6, "marginTop" to 4)), "boardWrap" to _pS(_uM("width" to "100%", "display" to "flex", "justifyContent" to "center", "marginBottom" to 14)), "board" to _pS(_uM("borderTopLeftRadius" to 14, "borderTopRightRadius" to 14, "borderBottomRightRadius" to 14, "borderBottomLeftRadius" to 14, "overflow" to "hidden", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.12)", "borderRightColor" to "rgba(0,0,0,0.12)", "borderBottomColor" to "rgba(0,0,0,0.12)", "borderLeftColor" to "rgba(0,0,0,0.12)", "boxSizing" to "border-box", "display" to "flex", "flexDirection" to "column")), "boardRow" to _pS(_uM("display" to "flex", "flexDirection" to "row")), "cell" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "center", "boxSizing" to "border-box", "flexShrink" to 0)), "light" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "#f0d9b5")), "dark" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "#b58863")), "selected" to _pS(_uM("borderTopWidth" to 3, "borderRightWidth" to 3, "borderBottomWidth" to 3, "borderLeftWidth" to 3, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(31,111,235,0.9)", "borderRightColor" to "rgba(31,111,235,0.9)", "borderBottomColor" to "rgba(31,111,235,0.9)", "borderLeftColor" to "rgba(31,111,235,0.9)", "boxShadow" to "inset 0 0 0 1px rgba(255,255,255,0.25)")), "piece" to _pS(_uM("fontSize" to 26, "lineHeight" to "26px")), "panel" to _pS(_uM("paddingTop" to 14, "paddingRight" to 14, "paddingBottom" to 14, "paddingLeft" to 14, "borderTopLeftRadius" to 14, "borderTopRightRadius" to 14, "borderBottomRightRadius" to 14, "borderBottomLeftRadius" to 14, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.08)", "borderRightColor" to "rgba(0,0,0,0.08)", "borderBottomColor" to "rgba(0,0,0,0.08)", "borderLeftColor" to "rgba(0,0,0,0.08)", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.02)")), "rowInfo" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to 8, "paddingRight" to 0, "paddingBottom" to 8, "paddingLeft" to 0, "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.06)")), "rowFirst" to _pS(_uM("borderTopWidth" to "medium", "borderTopStyle" to "none", "borderTopColor" to "#000000", "paddingTop" to 0)), "label" to _pS(_uM("fontSize" to 12, "opacity" to 0.6)), "value" to _pS(_uM("fontSize" to 14, "fontWeight" to "700")), "btns" to _pS(_uM("marginTop" to 12)), "btn" to _pS(_uM("height" to 42, "borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "borderBottomRightRadius" to 12, "borderBottomLeftRadius" to 12, "backgroundImage" to "none", "backgroundColor" to "rgba(31,111,235,0.12)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(31,111,235,0.25)", "borderRightColor" to "rgba(31,111,235,0.25)", "borderBottomColor" to "rgba(31,111,235,0.25)", "borderLeftColor" to "rgba(31,111,235,0.25)", "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "btnText" to _pS(_uM("fontSize" to 14, "fontWeight" to "700")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
