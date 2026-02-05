import { ref, onLoad, computed } from 'vue'

type Mode = 'ai' | 'online' | 'local'


const __sfc__ = defineComponent({
  __name: 'game',
  setup(__props) {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

const mode = ref<Mode>('local')

// ✅ UTS 更稳的 query 取参方式：先转成 Map/Record 再取值
onLoad((query: any) => {
  let m = ''

  if (query != null) {
    // 这里用 Record<string, any> 更贴近 UTS 可编译结构
    const q = query as Record<string, any>
    const raw = q['mode']

    // ✅ 不用 String(...)，用模板字符串转成 string
    if (raw != null) {
      m = `${raw}`
    }
  }

  if (m === 'ai' || m === 'online' || m === 'local') {
    mode.value = m as Mode
  } else {
    mode.value = 'local'
  }
})

const modeText = computed<string>(() => {
  if (mode.value === 'ai') return '人机对战 (AI)'
  if (mode.value === 'online') return '联机对战 (Online)'
  return '本地双人 (Local)'
})

function backToHome() {
  uni.switchTab({ url: '/pages/home/home' })
}

function toast(msg: string) {
  uni.showToast({ title: msg, icon: 'none' })
}

return (): any | null => {

  return _cE("view", _uM({ class: "page" }), [
    _cE("view", _uM({ class: "head" }), [
      _cE("text", _uM({ class: "h1" }), "Game"),
      _cE("text", _uM({ class: "sub" }), "mode: " + _tD(modeText.value), 1 /* TEXT */)
    ]),
    _cE("view", _uM({ class: "panel" }), [
      _cE("text", _uM({ class: "label" }), "当前模式"),
      _cE("text", _uM({ class: "value" }), _tD(modeText.value), 1 /* TEXT */),
      _cE("view", _uM({ class: "btns" }), [
        _cE("view", _uM({
          class: "btn",
          onClick: backToHome
        }), [
          _cE("text", _uM({ class: "btnText" }), "返回 Home")
        ]),
        _cE("view", _uM({
          class: "btn ghost",
          onClick: () => {toast('下一步：在此页面加入棋盘 UI')}
        }), [
          _cE("text", _uM({ class: "btnText" }), "下一步提示")
        ], 8 /* PROPS */, ["onClick"])
      ])
    ])
  ])
}
}

})
export default __sfc__
const GenPagesGameGameStyles = [_uM([["page", _pS(_uM([["paddingTop", 18], ["paddingRight", 18], ["paddingBottom", 18], ["paddingLeft", 18], ["display", "flex"], ["flexDirection", "column"]]))], ["head", _pS(_uM([["marginBottom", 14]]))], ["h1", _pS(_uM([["fontSize", 20], ["fontWeight", "700"]]))], ["sub", _pS(_uM([["fontSize", 12], ["opacity", 0.6], ["marginTop", 4]]))], ["panel", _pS(_uM([["paddingTop", 14], ["paddingRight", 14], ["paddingBottom", 14], ["paddingLeft", 14], ["borderTopLeftRadius", 14], ["borderTopRightRadius", 14], ["borderBottomRightRadius", 14], ["borderBottomLeftRadius", 14], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.08)"], ["borderRightColor", "rgba(0,0,0,0.08)"], ["borderBottomColor", "rgba(0,0,0,0.08)"], ["borderLeftColor", "rgba(0,0,0,0.08)"], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0.02)"]]))], ["label", _pS(_uM([["fontSize", 12], ["opacity", 0.6]]))], ["value", _pS(_uM([["marginTop", 8], ["fontSize", 16], ["fontWeight", "700"]]))], ["btns", _pS(_uM([["marginTop", 14]]))], ["btn", _pS(_uM([["height", 42], ["borderTopLeftRadius", 12], ["borderTopRightRadius", 12], ["borderBottomRightRadius", 12], ["borderBottomLeftRadius", 12], ["backgroundImage", "none"], ["backgroundColor", "rgba(31,111,235,0.12)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(31,111,235,0.25)"], ["borderRightColor", "rgba(31,111,235,0.25)"], ["borderBottomColor", "rgba(31,111,235,0.25)"], ["borderLeftColor", "rgba(31,111,235,0.25)"], ["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"], ["marginBottom", 10]]))], ["btnText", _pS(_uM([["fontSize", 14], ["fontWeight", "700"]]))], ["ghost", _pS(_uM([["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0.03)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.08)"], ["borderRightColor", "rgba(0,0,0,0.08)"], ["borderBottomColor", "rgba(0,0,0,0.08)"], ["borderLeftColor", "rgba(0,0,0,0.08)"]]))]])]
