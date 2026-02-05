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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
open class GenPagesHomeHome : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHomeHome) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHomeHome
            val _cache = __ins.renderCache
            fun gen_goGame_fn(mode: GameMode) {
                uni_navigateTo(NavigateToOptions(url = "/pages/game/game?mode=" + mode))
            }
            val goGame = ::gen_goGame_fn
            return fun(): Any? {
                return _cE("view", _uM("class" to "page"), _uA(
                    _cE("view", _uM("class" to "header"), _uA(
                        _cE("image", _uM("class" to "logo", "src" to "/static/logo.png", "mode" to "aspectFit")),
                        _cE("view", _uM("class" to "titles"), _uA(
                            _cE("text", _uM("class" to "h1"), "uni-Chess"),
                            _cE("text", _uM("class" to "sub"), "Home")
                        ))
                    )),
                    _cE("view", _uM("class" to "cards"), _uA(
                        _cE("view", _uM("class" to "card", "onClick" to fun(){
                            goGame("ai")
                        }
                        ), _uA(
                            _cE("text", _uM("class" to "cardTitle"), "人机对战"),
                            _cE("text", _uM("class" to "cardDesc"), "和 AI 下棋")
                        ), 8, _uA(
                            "onClick"
                        )),
                        _cE("view", _uM("class" to "card", "onClick" to fun(){
                            goGame("online")
                        }
                        ), _uA(
                            _cE("text", _uM("class" to "cardTitle"), "联机对战"),
                            _cE("text", _uM("class" to "cardDesc"), "匹配 / 房间")
                        ), 8, _uA(
                            "onClick"
                        )),
                        _cE("view", _uM("class" to "card", "onClick" to fun(){
                            goGame("local")
                        }
                        ), _uA(
                            _cE("text", _uM("class" to "cardTitle"), "本地双人"),
                            _cE("text", _uM("class" to "cardDesc"), "同屏两人对弈")
                        ), 8, _uA(
                            "onClick"
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
                return _uM("page" to _pS(_uM("paddingTop" to 18, "paddingRight" to 18, "paddingBottom" to 18, "paddingLeft" to 18, "display" to "flex", "flexDirection" to "column")), "header" to _pS(_uM("display" to "flex", "alignItems" to "center", "marginBottom" to 16)), "logo" to _pS(_uM("width" to 44, "height" to 44, "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "marginRight" to 12)), "titles" to _pS(_uM("display" to "flex", "flexDirection" to "column")), "h1" to _pS(_uM("fontSize" to 20, "fontWeight" to "700")), "sub" to _pS(_uM("fontSize" to 12, "opacity" to 0.6, "marginTop" to 2)), "cards" to _pS(_uM("display" to "flex", "flexDirection" to "column")), "card" to _pS(_uM("paddingTop" to 14, "paddingRight" to 14, "paddingBottom" to 14, "paddingLeft" to 14, "borderTopLeftRadius" to 14, "borderTopRightRadius" to 14, "borderBottomRightRadius" to 14, "borderBottomLeftRadius" to 14, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.08)", "borderRightColor" to "rgba(0,0,0,0.08)", "borderBottomColor" to "rgba(0,0,0,0.08)", "borderLeftColor" to "rgba(0,0,0,0.08)", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.02)", "marginBottom" to 12)), "cardTitle" to _pS(_uM("fontSize" to 16, "fontWeight" to "700")), "cardDesc" to _pS(_uM("marginTop" to 6, "fontSize" to 12, "opacity" to 0.7)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
