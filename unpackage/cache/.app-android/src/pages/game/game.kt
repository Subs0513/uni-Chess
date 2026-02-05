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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
import io.dcloud.uniapp.extapi.switchTab as uni_switchTab
open class GenPagesGameGame : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesGameGame) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesGameGame
            val _cache = __ins.renderCache
            val mode = ref<Mode>("local")
            onLoad(fun(query: Any){
                var m = ""
                if (query != null) {
                    val q = query as Record<String, Any>
                    val raw = q["mode"]
                    if (raw != null) {
                        m = "" + raw
                    }
                }
                if (m === "ai" || m === "online" || m === "local") {
                    mode.value = m as Mode
                } else {
                    mode.value = "local"
                }
            }
            )
            val modeText = computed<String>(fun(): String {
                if (mode.value === "ai") {
                    return "人机对战 (AI)"
                }
                if (mode.value === "online") {
                    return "联机对战 (Online)"
                }
                return "本地双人 (Local)"
            }
            )
            fun gen_backToHome_fn() {
                uni_switchTab(SwitchTabOptions(url = "/pages/home/home"))
            }
            val backToHome = ::gen_backToHome_fn
            fun gen_toast_fn(msg: String) {
                uni_showToast(ShowToastOptions(title = msg, icon = "none"))
            }
            val toast = ::gen_toast_fn
            return fun(): Any? {
                return _cE("view", _uM("class" to "page"), _uA(
                    _cE("view", _uM("class" to "head"), _uA(
                        _cE("text", _uM("class" to "h1"), "Game"),
                        _cE("text", _uM("class" to "sub"), "mode: " + _tD(modeText.value), 1)
                    )),
                    _cE("view", _uM("class" to "panel"), _uA(
                        _cE("text", _uM("class" to "label"), "当前模式"),
                        _cE("text", _uM("class" to "value"), _tD(modeText.value), 1),
                        _cE("view", _uM("class" to "btns"), _uA(
                            _cE("view", _uM("class" to "btn", "onClick" to backToHome), _uA(
                                _cE("text", _uM("class" to "btnText"), "返回 Home")
                            )),
                            _cE("view", _uM("class" to "btn ghost", "onClick" to fun(){
                                toast("下一步：在此页面加入棋盘 UI")
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "btnText"), "下一步提示")
                            ), 8, _uA(
                                "onClick"
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
                return _uM("page" to _pS(_uM("paddingTop" to 18, "paddingRight" to 18, "paddingBottom" to 18, "paddingLeft" to 18, "display" to "flex", "flexDirection" to "column")), "head" to _pS(_uM("marginBottom" to 14)), "h1" to _pS(_uM("fontSize" to 20, "fontWeight" to "700")), "sub" to _pS(_uM("fontSize" to 12, "opacity" to 0.6, "marginTop" to 4)), "panel" to _pS(_uM("paddingTop" to 14, "paddingRight" to 14, "paddingBottom" to 14, "paddingLeft" to 14, "borderTopLeftRadius" to 14, "borderTopRightRadius" to 14, "borderBottomRightRadius" to 14, "borderBottomLeftRadius" to 14, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.08)", "borderRightColor" to "rgba(0,0,0,0.08)", "borderBottomColor" to "rgba(0,0,0,0.08)", "borderLeftColor" to "rgba(0,0,0,0.08)", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.02)")), "label" to _pS(_uM("fontSize" to 12, "opacity" to 0.6)), "value" to _pS(_uM("marginTop" to 8, "fontSize" to 16, "fontWeight" to "700")), "btns" to _pS(_uM("marginTop" to 14)), "btn" to _pS(_uM("height" to 42, "borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "borderBottomRightRadius" to 12, "borderBottomLeftRadius" to 12, "backgroundImage" to "none", "backgroundColor" to "rgba(31,111,235,0.12)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(31,111,235,0.25)", "borderRightColor" to "rgba(31,111,235,0.25)", "borderBottomColor" to "rgba(31,111,235,0.25)", "borderLeftColor" to "rgba(31,111,235,0.25)", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "marginBottom" to 10)), "btnText" to _pS(_uM("fontSize" to 14, "fontWeight" to "700")), "ghost" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.03)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.08)", "borderRightColor" to "rgba(0,0,0,0.08)", "borderBottomColor" to "rgba(0,0,0,0.08)", "borderLeftColor" to "rgba(0,0,0,0.08)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
