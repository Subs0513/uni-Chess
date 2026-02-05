type GameMode = 'ai' | 'online' | 'local'


const __sfc__ = defineComponent({
  __name: 'home',
  setup(__props) {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

function goGame(mode: GameMode) {
  uni.navigateTo({
    url: `/pages/game/game?mode=${mode}`
  })
}

return (): any | null => {

  return _cE("view", _uM({ class: "page" }), [
    _cE("view", _uM({ class: "header" }), [
      _cE("image", _uM({
        class: "logo",
        src: "/static/logo.png",
        mode: "aspectFit"
      })),
      _cE("view", _uM({ class: "titles" }), [
        _cE("text", _uM({ class: "h1" }), "uni-Chess"),
        _cE("text", _uM({ class: "sub" }), "Home")
      ])
    ]),
    _cE("view", _uM({ class: "cards" }), [
      _cE("view", _uM({
        class: "card",
        onClick: () => {goGame('ai')}
      }), [
        _cE("text", _uM({ class: "cardTitle" }), "人机对战"),
        _cE("text", _uM({ class: "cardDesc" }), "和 AI 下棋")
      ], 8 /* PROPS */, ["onClick"]),
      _cE("view", _uM({
        class: "card",
        onClick: () => {goGame('online')}
      }), [
        _cE("text", _uM({ class: "cardTitle" }), "联机对战"),
        _cE("text", _uM({ class: "cardDesc" }), "匹配 / 房间")
      ], 8 /* PROPS */, ["onClick"]),
      _cE("view", _uM({
        class: "card",
        onClick: () => {goGame('local')}
      }), [
        _cE("text", _uM({ class: "cardTitle" }), "本地双人"),
        _cE("text", _uM({ class: "cardDesc" }), "同屏两人对弈")
      ], 8 /* PROPS */, ["onClick"])
    ])
  ])
}
}

})
export default __sfc__
const GenPagesHomeHomeStyles = [_uM([["page", _pS(_uM([["paddingTop", 18], ["paddingRight", 18], ["paddingBottom", 18], ["paddingLeft", 18], ["display", "flex"], ["flexDirection", "column"]]))], ["header", _pS(_uM([["display", "flex"], ["alignItems", "center"], ["marginBottom", 16]]))], ["logo", _pS(_uM([["width", 44], ["height", 44], ["borderTopLeftRadius", 10], ["borderTopRightRadius", 10], ["borderBottomRightRadius", 10], ["borderBottomLeftRadius", 10], ["marginRight", 12]]))], ["titles", _pS(_uM([["display", "flex"], ["flexDirection", "column"]]))], ["h1", _pS(_uM([["fontSize", 20], ["fontWeight", "700"]]))], ["sub", _pS(_uM([["fontSize", 12], ["opacity", 0.6], ["marginTop", 2]]))], ["cards", _pS(_uM([["display", "flex"], ["flexDirection", "column"]]))], ["card", _pS(_uM([["paddingTop", 14], ["paddingRight", 14], ["paddingBottom", 14], ["paddingLeft", 14], ["borderTopLeftRadius", 14], ["borderTopRightRadius", 14], ["borderBottomRightRadius", 14], ["borderBottomLeftRadius", 14], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.08)"], ["borderRightColor", "rgba(0,0,0,0.08)"], ["borderBottomColor", "rgba(0,0,0,0.08)"], ["borderLeftColor", "rgba(0,0,0,0.08)"], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0.02)"], ["marginBottom", 12]]))], ["cardTitle", _pS(_uM([["fontSize", 16], ["fontWeight", "700"]]))], ["cardDesc", _pS(_uM([["marginTop", 6], ["fontSize", 12], ["opacity", 0.7]]))]])]
