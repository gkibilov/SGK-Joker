(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-68c9bb7a"],{"7b1e":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"playingCards"},[a("div",{staticClass:"some-page-wrapper"},[t.showResumePolling?a("a",{on:{click:function(e){return t.startPolling()}}},[t._v("resume polling")]):t._e(),a("div",{staticClass:"header-row"},[a("div",{staticClass:"account"},[t.players.length>0?a("div",{attrs:{id:"header"}},t._l(4,(function(e){return a("div",{key:e},[t.players[e].name?a("span",[t._v(t._s(t.players[e].name))]):t._e(),t._l(t.players[e].calls.length,(function(s){return a("div",{key:s,staticClass:"scores"},[1===e?a("span",{staticClass:"separator"},[t._v(t._s(s))]):t._e(),t._v(" "+t._s(t.players[e].calls[s-1])+" - "+t._s(t.players[e].scores[s-1])+" ")])})),a("div",{staticClass:"total"},[t._v(" "+t._s(t.players[e].totalScore)+" ")])],2)})),0):t._e(),a("div",{staticClass:"attribute"},[t._v("|---| -200")]),t.currentState&&t.currentState.currentPlay&&t.currentState.currentPlay.huntLevel&&t.currentState.currentPlay.huntLevel>0?a("div",{staticClass:"attribute hunt",attrs:{title:"შენტენვა"}},[t._v("🐷"+t._s(t.currentState.currentPlay.huntLevel))]):t._e(),t.currentState&&t.currentState.currentPlay&&t.currentState.currentPlay.huntLevel&&t.currentState.currentPlay.huntLevel<0?a("div",{staticClass:"attribute huntout",attrs:{title:"წაგლეჯვა"}},[t._v(" "+t._s(-1*t.currentState.currentPlay.huntLevel))]):t._e()]),a("div",{staticClass:"kozyr"},[t.kozyr&&t.deck[t.kozyr.id]?a("div",{staticClass:"card",class:t.deck[t.kozyr.id].suite},[a("span",{staticClass:"rank"},[t._v(t._s(t.deck[t.kozyr.id].type))]),a("span",{staticClass:"suit"}),a("span",{staticClass:"rank rank-bottom"},[t._v(t._s(t.deck[t.kozyr.id].type))])]):t._e(),t.kozyr&&-1===t.kozyr.id?a("div",{staticClass:"card",class:t.kozyr.suite.toLowerCase()},[a("span",{staticClass:"rank"}),a("span",{staticClass:"suit"}),a("span",{staticClass:"rank rank-bottom"})]):t._e()])]),a("div",{staticClass:"row"},[a("div",{staticClass:"column inlineCards"},[t.opponents[1]?a("div",{staticClass:"playerName top"},[this.currentState.currentTurnPosition===t.opponents[1].position?a("div",{staticClass:"yourTurn"}):t._e(),t._v(" "+t._s(t.opponents[1].name)+" ("+t._s(t.opponents[1].call||"0")+"/"+t._s(t.opponents[1].taken)+") ")]):t._e(),a("div",{staticClass:"privateCard0 cardBack"})])]),a("div",{staticClass:"row"},[a("div",{staticClass:"column"},[t.opponents[0]?a("div",{staticClass:"playerName right"},[this.currentState.currentTurnPosition===t.opponents[0].position?a("div",{staticClass:"yourTurn"}):t._e(),t._v(" "+t._s(t.opponents[0].name)+" ("+t._s(t.opponents[0].call||"0")+"/"+t._s(t.opponents[0].taken)+") ")]):t._e(),a("div",{staticClass:"privateCard90 cardBack90"})]),a("div",{staticClass:"column content"},[a("div",{staticClass:"middleActoins"},t._l(t.actions,(function(e){return a("div",{key:e.cardId,staticClass:"card",class:t.deck[e.cardId].suite},[a("span",{staticClass:"rank"},[t._v(t._s(t.deck[e.cardId].type))]),a("span",{staticClass:"suit"}),a("span",{staticClass:"rank rank-bottom"},[t._v(t._s(t.deck[e.cardId].type))])])})),0)]),a("div",{staticClass:"column"},[t.opponents[2]?a("div",{staticClass:"playerName left"},[this.currentState.currentTurnPosition===t.opponents[2].position?a("div",{staticClass:"yourTurn"}):t._e(),t._v(" "+t._s(t.opponents[2].name)+" ("+t._s(t.opponents[2].call||"0")+"/"+t._s(t.opponents[2].taken)+") ")]):t._e(),a("div",{staticClass:"privateCard90 cardBack90"})])]),a("div",{staticClass:"footer-row"},[a("div",{staticClass:"column"},["CALLS_MADE"!==t.currentState.status&&"PLAY_DONE"!==t.currentState.status&&"PLAY_STARTED"!==t.currentState.status||t.currentState.currentTurnPosition!==t.me.position?t._e():a("div",{staticClass:"callBlock"},[a("span",{staticClass:"callUnit"},[t._v("ჰე ჩამოდი!!")])]),"DEALT"===t.currentState.status&&t.currentState.currentTurnPosition===t.me.position&&null!=t.kozyr?a("div",{staticClass:"callBlock"},[0!=t.me.cantCallNumer?a("span",{staticClass:"callUnit",on:{click:function(e){return t.call(0)}}},[t._v("Pass")]):t._e(),t._l(t.currentState.numCards,(function(e){return a("div",{key:e,staticClass:"template",on:{click:function(a){return t.call(e)}}},[e&&t.me.cantCallNumer!=e?a("span",{staticClass:"callUnit"},[t._v(t._s(e))]):t._e()])}))],2):t._e(),t.showJokerAction?a("div",{staticClass:"jokerAction"},[a("div",{staticClass:"close",on:{click:function(e){return t.closeJokerActions()}}},[t._v("X")]),a("div",{staticClass:"item",on:{click:function(e){return t.selectJokerAction("WANT")}}},[t._v("Want")]),a("div",{staticClass:"item",on:{click:function(e){return t.selectJokerAction("TAKE")}}},[t._v("Take")])]):t._e(),t.showJokerActionSuite?a("div",{staticClass:"jokerAction"},[a("div",{staticClass:"close",on:{click:function(e){return t.closeJokerActions()}}},[t._v("X")]),a("div",{staticClass:"want"},[a("div",{staticClass:"item diamond",on:{click:function(e){return t.selectJokerSuite("DIAMOND")}}}),a("div",{staticClass:"item hart",on:{click:function(e){return t.selectJokerSuite("HART")}}}),a("div",{staticClass:"item spade",on:{click:function(e){return t.selectJokerSuite("SPADE")}}}),a("div",{staticClass:"item club",on:{click:function(e){return t.selectJokerSuite("CLUB")}}})])]):t._e(),"DEALT"===t.currentState.status&&t.currentState.currentTurnPosition===t.me.position&&null==t.kozyr?a("div",{staticClass:"jokerAction"},[a("div",{staticClass:"close",on:{click:function(e){return t.closeJokerActions()}}},[t._v("X")]),a("div",{staticClass:"want"},[a("div",{staticClass:"item diamond",on:{click:function(e){return t.setKozyr("DIAMOND")}}}),a("div",{staticClass:"item hart",on:{click:function(e){return t.setKozyr("HART")}}}),a("div",{staticClass:"item spade",on:{click:function(e){return t.setKozyr("SPADE")}}}),a("div",{staticClass:"item club",on:{click:function(e){return t.setKozyr("CLUB")}}}),a("div",{staticClass:"item BEZ",on:{click:function(e){return t.setKozyr("BEZ")}}})])]):t._e(),a("div",{staticClass:"playerName bottom"},[t._v(t._s(t.me.name)+"("+t._s(t.me.call||"0")+"/"+t._s(t.me.taken)+")")]),a("div",{staticClass:"inlineCards"},[t.me.cards&&t.me.cards.length>0?[a("div",{staticClass:"cardWrapper"},t._l(t.me.cards,(function(e){return a("div",{key:e.id,staticClass:"card",class:t.deck[e.id].suite,on:{click:function(a){return t.hitAction(e)}}},[a("span",{staticClass:"rank"},[t._v(t._s(t.deck[e.id].type))]),a("span",{staticClass:"suit"}),a("span",{staticClass:"rank rank-bottom"},[t._v(t._s(t.deck[e.id].type))])])})),0)]:t._e()],2)])])])])},i=[],n=(a("99af"),a("4160"),a("159b"),a("d4ec")),r=a("bee2"),c=a("2caf"),o=a("262e"),u=a("9ab4"),l=a("bc3a"),d=a.n(l),p=a("60a3"),y=function(t){Object(o["a"])(a,t);var e=Object(c["a"])(a);function a(){var t;return Object(n["a"])(this,a),t=e.apply(this,arguments),t.currentState={},t.actions=[],t.deck={1:{id:1,suite:"bez",type:"JOKER"},2:{id:2,suite:"bez",type:"JOKER"},3:{id:3,suite:"spade",type:"7"},4:{id:4,suite:"spade",type:"8"},5:{id:5,suite:"spade",type:"9"},6:{id:6,suite:"spade",type:"10"},7:{id:7,suite:"spade",type:"J"},8:{id:8,suite:"spade",type:"Q"},9:{id:9,suite:"spade",type:"K"},10:{id:10,suite:"spade",type:"A"},11:{id:11,suite:"club",type:"7"},12:{id:12,suite:"club",type:"8"},13:{id:13,suite:"club",type:"9"},14:{id:14,suite:"club",type:"10"},15:{id:15,suite:"club",type:"J"},16:{id:16,suite:"club",type:"Q"},17:{id:17,suite:"club",type:"K"},18:{id:18,suite:"club",type:"A"},19:{id:19,suite:"diamond",type:"6"},20:{id:20,suite:"diamond",type:"7"},21:{id:21,suite:"diamond",type:"8"},22:{id:22,suite:"diamond",type:"9"},23:{id:23,suite:"diamond",type:"10"},24:{id:24,suite:"diamond",type:"J"},25:{id:25,suite:"diamond",type:"Q"},26:{id:26,suite:"diamond",type:"K"},27:{id:27,suite:"diamond",type:"A"},28:{id:28,suite:"hart",type:"6"},29:{id:29,suite:"hart",type:"7"},30:{id:30,suite:"hart",type:"8"},31:{id:31,suite:"hart",type:"9"},32:{id:32,suite:"hart",type:"10"},33:{id:33,suite:"hart",type:"J"},34:{id:34,suite:"hart",type:"Q"},35:{id:35,suite:"hart",type:"K"},36:{id:36,suite:"hart",type:"A"}},t.me={},t.players=[],t.opponents=[],t.kozyr="none",t.polling=-1,t.gameId="",t.showJokerAction=!1,t.showJokerActionSuite=!1,t.jokerAction="",t.joker={},t.version=0,t.showResumePolling=!1,t.showSetKozyr=!1,t.appHost="https://joker.sgkperformance.com",t}return Object(r["a"])(a,[{key:"created",value:function(){this.startPolling()}},{key:"startPolling",value:function(){var t=this;this.refreshState(),this.gameId=this.$route.params.gameId,this.polling=setInterval((function(){t.refreshState()}),2e3)}},{key:"hitAction",value:function(t){1===t.id||2===t.id?(this.showJokerAction=!0,this.joker=t):this.action(t,"")}},{key:"selectJokerAction",value:function(t){this.showJokerAction=!1,this.currentState.currentTurnPosition===this.currentState.actingPlayerPosition?(this.showJokerActionSuite=!0,this.jokerAction=t):this.action(this.joker,t)}},{key:"selectJokerSuite",value:function(t){var e="".concat(this.jokerAction,"_").concat(t);this.showJokerAction=!1,this.showJokerActionSuite=!1,this.jokerAction="",this.action(this.joker,e),this.joker={}}},{key:"closeJokerActions",value:function(){this.showJokerAction=!1,this.showJokerActionSuite=!1,this.jokerAction="",this.joker={}}},{key:"action",value:function(t,e){var a=this;if(this.currentState.currentTurnPosition&&this.currentState.actingPlayerPosition){var s=this.me;s&&s.cards&&s.cards[0]&&s.cards[0]&&(this.currentState.currentTurnPosition!==this.currentState.actingPlayerPosition?d.a.get("".concat(this.appHost,"/reaction?playerId=").concat(s.id,"&cardId=").concat(t.id,"&gameId=").concat(this.gameId,"&jokerReaction=").concat(e)).then((function(t){a.currentState=t.data.state,a.currentState&&a.currentState.currentPlay&&a.currentState.currentPlay.actions&&(a.actions=a.currentState.currentPlay.actions)})).catch((function(t){a.handleError(t)})):d.a.get("".concat(this.appHost,"/action?playerId=").concat(s.id,"&cardId=").concat(t.id,"&gameId=").concat(this.gameId,"&jokerAction=").concat(e)).then((function(t){a.currentState=t.data.state,a.currentState&&a.currentState.currentPlay&&a.currentState.currentPlay.actions&&(a.actions=a.currentState.currentPlay.actions)})).catch((function(t){a.handleError(t)})))}}},{key:"call",value:function(t){var e=this;if(this.currentState.currentTurnPosition){var a=this.players[this.currentState.currentTurnPosition];d.a.get("".concat(this.appHost,"/call?playerId=").concat(a.id,"&wantQty=").concat(t,"&gameId=").concat(this.gameId)).then((function(t){e.currentState=t.data.state})).catch((function(t){e.handleError(t)}))}}},{key:"setKozyr",value:function(t){var e=this;d.a.get("".concat(this.appHost,"/setKozyr?playerId=").concat(this.me.id,"&kozyrSuite=").concat(t,"&gameId=").concat(this.gameId)).then((function(t){e.currentState=t.data.state,e.showSetKozyr=!1})).catch((function(t){e.handleError(t)}))}},{key:"refreshState",value:function(){var t=this,e=this.$route.params,a=e.gameId,s=e.id;d.a.get("".concat(this.appHost,"/getPlayersState?playerId=").concat(s,"&gameId=").concat(a)).then((function(e){e.data.state.version!==t.version&&(t.currentState=e.data.state,t.currentState&&t.currentState.currentPlay&&t.currentState.currentPlay.actions&&(t.actions=t.currentState.currentPlay.actions),t.me=e.data.player,t.players[e.data.player.position]=e.data.player,t.opponents=e.data.opponents,e.data.opponents.forEach((function(e){e.position&&(t.players[e.position]=e)})),t.kozyr=e.data.state.currentPlay.kozyr,t.currentState.roundNumber&&t.currentState.roundNumber>8&&t.currentState.roundNumber<13&&t.currentState.currentTurnPosition===t.currentState.actingPlayerPosition&&(t.showSetKozyr=!0))})).catch((function(e){clearInterval(t.polling),t.showResumePolling=!0,t.handleError(e)}))}},{key:"beforeDestroy",value:function(){clearInterval(this.polling),this.polling=-1}},{key:"handleError",value:function(t){t.response&&this.$alert(t.response.data.message)}}]),a}(p["b"]);y=Object(u["a"])([p["a"]],y);var k=y,h=k,v=(a("efa8"),a("2877")),m=Object(v["a"])(h,s,i,!1,null,"a79170f2",null);e["default"]=m.exports},df72:function(t,e,a){},efa8:function(t,e,a){"use strict";var s=a("df72"),i=a.n(s);i.a}}]);
//# sourceMappingURL=chunk-68c9bb7a.4cbe5484.js.map