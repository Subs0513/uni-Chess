import { ref } from 'vue'

type SwitchChangeDetail = { __$originalPosition?: UTSSourceMapPosition<"SwitchChangeDetail", "pages/me/me.uvue", 29, 6>;
  value: boolean
}

type SwitchChangeEvent = { __$originalPosition?: UTSSourceMapPosition<"SwitchChangeEvent", "pages/me/me.uvue", 33, 6>;
  detail: SwitchChangeDetail
}


const __sfc__ = defineComponent({
  __name: 'me',
  setup(__props) {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

const sound = ref(true)
const vibrate = ref(true)
const theme = ref('Classic')

function onSoundChange(e: SwitchChangeEvent) {
  sound.value = e.detail.value
}

function onVibrateChange(e: SwitchChangeEvent) {
  vibrate.value = e.detail.value
}

return (): any | null => {

const _component_switch = resolveComponent("switch")

  return _cE("view", _uM({ class: "page" }), [
    _cE("text", _uM({ class: "h1" }), "Me"),
    _cE("view", _uM({ class: "section" }), [
      _cE("text", _uM({ class: "secTitle" }), "设置（占位）"),
      _cE("view", _uM({ class: "row" }), [
        _cE("text", null, "音效"),
        _cV(_component_switch, _uM({
          checked: sound.value,
          onChange: onSoundChange
        }), null, 8 /* PROPS */, ["checked"])
      ]),
      _cE("view", _uM({ class: "row" }), [
        _cE("text", null, "震动"),
        _cV(_component_switch, _uM({
          checked: vibrate.value,
          onChange: onVibrateChange
        }), null, 8 /* PROPS */, ["checked"])
      ]),
      _cE("view", _uM({ class: "row" }), [
        _cE("text", null, "棋盘主题"),
        _cE("text", _uM({ class: "value" }), _tD(theme.value), 1 /* TEXT */)
      ])
    ])
  ])
}
}

})
export default __sfc__
const GenPagesMeMeStyles = [_uM([["page", _pS(_uM([["paddingTop", 18], ["paddingRight", 18], ["paddingBottom", 18], ["paddingLeft", 18], ["display", "flex"], ["flexDirection", "column"]]))], ["h1", _pS(_uM([["fontSize", 20], ["fontWeight", "700"], ["marginBottom", 14]]))], ["section", _pS(_uM([["paddingTop", 14], ["paddingRight", 14], ["paddingBottom", 14], ["paddingLeft", 14], ["borderTopLeftRadius", 14], ["borderTopRightRadius", 14], ["borderBottomRightRadius", 14], ["borderBottomLeftRadius", 14], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.08)"], ["borderRightColor", "rgba(0,0,0,0.08)"], ["borderBottomColor", "rgba(0,0,0,0.08)"], ["borderLeftColor", "rgba(0,0,0,0.08)"], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0.02)"], ["display", "flex"], ["flexDirection", "column"]]))], ["secTitle", _pS(_uM([["fontSize", 14], ["fontWeight", "700"], ["opacity", 0.8], ["marginBottom", 12]]))], ["row", _pS(_uM([["display", "flex"], ["alignItems", "center"], ["justifyContent", "space-between"], ["paddingTop", 10], ["paddingRight", 0], ["paddingBottom", 10], ["paddingLeft", 0], ["borderTopWidth", 1], ["borderTopStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.06)"], ["borderTopWidth:first-of-type", "medium"], ["borderTopStyle:first-of-type", "none"], ["borderTopColor:first-of-type", "#000000"], ["paddingTop:first-of-type", 0]]))], ["value", _pS(_uM([["opacity", 0.7]]))]])]
