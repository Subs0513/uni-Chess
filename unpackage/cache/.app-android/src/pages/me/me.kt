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
open class GenPagesMeMe : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesMeMe) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesMeMe
            val _cache = __ins.renderCache
            val sound = ref(true)
            val vibrate = ref(true)
            val theme = ref("Classic")
            fun gen_onSoundChange_fn(e: SwitchChangeEvent) {
                sound.value = e.detail.value
            }
            val onSoundChange = ::gen_onSoundChange_fn
            fun gen_onVibrateChange_fn(e: SwitchChangeEvent) {
                vibrate.value = e.detail.value
            }
            val onVibrateChange = ::gen_onVibrateChange_fn
            return fun(): Any? {
                val _component_switch = resolveComponent("switch")
                return _cE("view", _uM("class" to "page"), _uA(
                    _cE("text", _uM("class" to "h1"), "Me"),
                    _cE("view", _uM("class" to "section"), _uA(
                        _cE("text", _uM("class" to "secTitle"), "设置（占位）"),
                        _cE("view", _uM("class" to "row"), _uA(
                            _cE("text", null, "音效"),
                            _cV(_component_switch, _uM("checked" to sound.value, "onChange" to onSoundChange), null, 8, _uA(
                                "checked"
                            ))
                        )),
                        _cE("view", _uM("class" to "row"), _uA(
                            _cE("text", null, "震动"),
                            _cV(_component_switch, _uM("checked" to vibrate.value, "onChange" to onVibrateChange), null, 8, _uA(
                                "checked"
                            ))
                        )),
                        _cE("view", _uM("class" to "row"), _uA(
                            _cE("text", null, "棋盘主题"),
                            _cE("text", _uM("class" to "value"), _tD(theme.value), 1)
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
                return _uM("page" to _pS(_uM("paddingTop" to 18, "paddingRight" to 18, "paddingBottom" to 18, "paddingLeft" to 18, "display" to "flex", "flexDirection" to "column")), "h1" to _pS(_uM("fontSize" to 20, "fontWeight" to "700", "marginBottom" to 14)), "section" to _pS(_uM("paddingTop" to 14, "paddingRight" to 14, "paddingBottom" to 14, "paddingLeft" to 14, "borderTopLeftRadius" to 14, "borderTopRightRadius" to 14, "borderBottomRightRadius" to 14, "borderBottomLeftRadius" to 14, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.08)", "borderRightColor" to "rgba(0,0,0,0.08)", "borderBottomColor" to "rgba(0,0,0,0.08)", "borderLeftColor" to "rgba(0,0,0,0.08)", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.02)", "display" to "flex", "flexDirection" to "column")), "secTitle" to _pS(_uM("fontSize" to 14, "fontWeight" to "700", "opacity" to 0.8, "marginBottom" to 12)), "row" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to 10, "paddingRight" to 0, "paddingBottom" to 10, "paddingLeft" to 0, "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.06)", "borderTopWidth:first-of-type" to "medium", "borderTopStyle:first-of-type" to "none", "borderTopColor:first-of-type" to "#000000", "paddingTop:first-of-type" to 0)), "value" to _pS(_uM("opacity" to 0.7)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
