(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-orders-order"],{2653:function(t,i,e){"use strict";(function(t){var s=e("4ea4");e("a434"),Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var a=e("b911"),n=s(e("65df")),o={data:function(){return{orderList:[[],[],[],[]],show_:!0,list:[{name:"待支付"},{name:"待消费"},{name:"已完成"},{name:"全部订单"}],current:3,swiperCurrent:3,tabsHeight:0,dx:0,loadStatus:["loadmore","loadmore","loadmore","loadmore"]}},created:function(){},onLoad:function(){this.getOrderList(0),this.getOrderList(1),this.getOrderList(2),this.getOrderList(3)},mounted:function(){},computed:{},methods:{reachBottom:function(){var i=this;t("log","reachBottom"," at pages/orders/order.vue:152"),2!=this.current&&(this.loadStatus.splice(this.current,1,"loading"),setTimeout((function(){i.getOrderList(i.current)}),1200))},getOrderList:function(i){var e=this;this.show_=!0,t("log","getOrderList"," at pages/orders/order.vue:164");var s=n.default.get("user.mobile");this.$u.get(a.VUE_APP_URL+"/getOrdersByMobile?mobile="+s,{status:0==i?"0":1==i?"2":2==i?"4":"0,2,4"}).then((function(t){e.show_=!1;for(var s=0;s<t.data.length;s++){var a=t.data[s];0==a.status?a.status="待付款":2==a.status?a.status="待消费":3==a.status?a.status="支付失败":4==a.status&&(a.status="已结束")}e.orderList[i]=t.data,e.loadStatus.splice(e.current,1,"loadmore")}))},change:function(i){t("log","change"," at pages/orders/order.vue:190"),this.swiperCurrent=i,this.getOrderList(i)},transition:function(i){var e=i.detail.dx;t("log","transition"," at pages/orders/order.vue:195"),this.$refs.tabs.setDx(e)},animationfinish:function(i){var e=i.detail.current;t("log","animationfinish"," at pages/orders/order.vue:199"),this.$refs.tabs.setFinishCurrent(e),this.swiperCurrent=e,this.current=e},openinfo:function(i){t("log",i," at pages/orders/order.vue:205"),uni.navigateTo({url:"./order_detail?orderId="+i,success:function(t){},fail:function(){},complete:function(){}})}}};i.default=o}).call(this,e("0de9")["log"])},"3be0":function(t,i,e){"use strict";var s=e("5cfd"),a=e.n(s);a.a},5853:function(t,i,e){"use strict";e.r(i);var s=e("aabb"),a=e("5a14");for(var n in a)"default"!==n&&function(t){e.d(i,t,(function(){return a[t]}))}(n);e("3be0"),e("8b3a");var o,r=e("f0c5"),l=Object(r["a"])(a["default"],s["b"],s["c"],!1,null,"6e32f68d",null,!1,s["a"],o);i["default"]=l.exports},"5a14":function(t,i,e){"use strict";e.r(i);var s=e("2653"),a=e.n(s);for(var n in s)"default"!==n&&function(t){e.d(i,t,(function(){return s[t]}))}(n);i["default"]=a.a},"5cfd":function(t,i,e){var s=e("9258");"string"===typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);var a=e("4f06").default;a("66985200",s,!0,{sourceMap:!1,shadowMode:!1})},"8b3a":function(t,i,e){"use strict";var s=e("8f90"),a=e.n(s);a.a},"8f90":function(t,i,e){var s=e("dbbf");"string"===typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);var a=e("4f06").default;a("7d24bfe3",s,!0,{sourceMap:!1,shadowMode:!1})},9258:function(t,i,e){var s=e("24fb");i=s(!1),i.push([t.i,"\nbody.?%PAGE?%[data-v-6e32f68d]{background-color:#f2f2f2}",""]),t.exports=i},aabb:function(t,i,e){"use strict";e.d(i,"b",(function(){return a})),e.d(i,"c",(function(){return n})),e.d(i,"a",(function(){return s}));var s={uTabsSwiper:e("696a").default,uLoading:e("b8fe").default,uLoadmore:e("f052").default},a=function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("v-uni-view",[e("v-uni-view",{staticClass:"wrap"},[e("v-uni-view",{staticClass:"u-tabs-box"},[e("u-tabs-swiper",{ref:"tabs",attrs:{activeColor:"#f29100",list:t.list,current:t.current,"is-scroll":!1,swiperWidth:"750"},on:{change:function(i){arguments[0]=i=t.$handleEvent(i),t.change.apply(void 0,arguments)}}})],1),e("v-uni-view",{staticStyle:{display:"flex","justify-content":"center"}},[e("u-loading",{attrs:{show:t.show_,size:"68",mode:"flower"}})],1),e("v-uni-swiper",{staticClass:"swiper-box",attrs:{current:t.swiperCurrent},on:{transition:function(i){arguments[0]=i=t.$handleEvent(i),t.transition.apply(void 0,arguments)},animationfinish:function(i){arguments[0]=i=t.$handleEvent(i),t.animationfinish.apply(void 0,arguments)}}},[e("v-uni-swiper-item",{staticClass:"swiper-item"},[e("v-uni-scroll-view",{staticStyle:{height:"100%",width:"100%"},attrs:{"scroll-y":!0},on:{scrolltolower:function(i){arguments[0]=i=t.$handleEvent(i),t.reachBottom.apply(void 0,arguments)}}},[e("v-uni-view",{staticClass:"page-box"},[t._l(t.orderList[0],(function(i,s){return e("v-uni-view",{staticClass:"order",on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.openinfo(i.id)}}},[e("v-uni-view",{staticClass:"item"},[e("v-uni-view",{staticClass:"left"},[e("v-uni-image",{attrs:{src:"https://img3.qydimg.com/uploads/20170118/2015081439519090338632740540.jpg-old8QCqQ",mode:"aspectFill"}})],1),e("v-uni-view",{staticClass:"content"},[e("v-uni-view",[t._v(t._s(i.venueName))]),e("v-uni-view",{staticClass:"jiage",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v("数量 "+t._s(i.numbers)+"，总价"),e("font",{staticStyle:{color:"#ff850d"}},[t._v("￥"+t._s(i.totalAmount))])],1)],1),e("v-uni-view",{staticClass:"dinggou",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v(t._s(i.createDate))]),e("v-uni-view",{staticClass:"yuding"},[t._v(t._s(i.status))])],1)],1)],1)],1)})),e("u-loadmore",{attrs:{status:t.loadStatus[0],bgColor:"#f2f2f2"}})],2)],1)],1),e("v-uni-swiper-item",{staticClass:"swiper-item"},[e("v-uni-scroll-view",{staticStyle:{height:"100%",width:"100%"},attrs:{"scroll-y":!0},on:{scrolltolower:function(i){arguments[0]=i=t.$handleEvent(i),t.reachBottom.apply(void 0,arguments)}}},[e("v-uni-view",{staticClass:"page-box"},[t._l(t.orderList[1],(function(i,s){return e("v-uni-view",{staticClass:"order",on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.openinfo(i.id)}}},[e("v-uni-view",{staticClass:"item"},[e("v-uni-view",{staticClass:"left"},[e("v-uni-image",{attrs:{src:"https://img3.qydimg.com/uploads/20170118/2015081439519090338632740540.jpg-old8QCqQ",mode:"aspectFill"}})],1),e("v-uni-view",{staticClass:"content"},[e("v-uni-view",[t._v(t._s(i.venueName))]),e("v-uni-view",{staticClass:"jiage",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v("数量 "+t._s(i.numbers)+"，总价"),e("font",{staticStyle:{color:"#ff850d"}},[t._v("￥"+t._s(i.totalAmount))])],1)],1),e("v-uni-view",{staticClass:"dinggou",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v(t._s(i.createDate))]),e("v-uni-view",{staticClass:"yuding"},[t._v(t._s(i.status))])],1)],1)],1)],1)})),e("u-loadmore",{attrs:{status:t.loadStatus[1],bgColor:"#f2f2f2"}})],2)],1)],1),e("v-uni-swiper-item",{staticClass:"swiper-item"},[e("v-uni-scroll-view",{staticStyle:{height:"100%",width:"100%"},attrs:{"scroll-y":!0},on:{scrolltolower:function(i){arguments[0]=i=t.$handleEvent(i),t.reachBottom.apply(void 0,arguments)}}},[e("v-uni-view",{staticClass:"page-box"},[t._l(t.orderList[2],(function(i,s){return e("v-uni-view",{staticClass:"order",on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.openinfo(i.id)}}},[e("v-uni-view",{staticClass:"item"},[e("v-uni-view",{staticClass:"left"},[e("v-uni-image",{attrs:{src:"https://img3.qydimg.com/uploads/20170118/2015081439519090338632740540.jpg-old8QCqQ",mode:"aspectFill"}})],1),e("v-uni-view",{staticClass:"content"},[e("v-uni-view",[t._v(t._s(i.venueName))]),e("v-uni-view",{staticClass:"jiage",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v("数量 "+t._s(i.numbers)+"，总价"),e("font",{staticStyle:{color:"#ff850d"}},[t._v("￥"+t._s(i.totalAmount))])],1)],1),e("v-uni-view",{staticClass:"dinggou",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v(t._s(i.createDate))]),e("v-uni-view",{staticClass:"yuding"},[t._v(t._s(i.status))])],1)],1)],1)],1)})),e("u-loadmore",{attrs:{status:t.loadStatus[2],bgColor:"#f2f2f2"}})],2)],1)],1),e("v-uni-swiper-item",{staticClass:"swiper-item"},[e("v-uni-scroll-view",{staticStyle:{height:"100%",width:"100%"},attrs:{"scroll-y":!0},on:{scrolltolower:function(i){arguments[0]=i=t.$handleEvent(i),t.reachBottom.apply(void 0,arguments)}}},[e("v-uni-view",{staticClass:"page-box"},[t._l(t.orderList[3],(function(i,s){return e("v-uni-view",{staticClass:"order",on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.openinfo(i.id)}}},[e("v-uni-view",{staticClass:"item"},[e("v-uni-view",{staticClass:"left"},[e("v-uni-image",{attrs:{src:"https://img3.qydimg.com/uploads/20170118/2015081439519090338632740540.jpg-old8QCqQ",mode:"aspectFill"}})],1),e("v-uni-view",{staticClass:"content"},[e("v-uni-view",[t._v(t._s(i.venueName))]),e("v-uni-view",{staticClass:"jiage",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v("数量 "+t._s(i.numbers)+"，总价"),e("font",{staticStyle:{color:"#ff850d"}},[t._v("￥"+t._s(i.totalAmount))])],1)],1),e("v-uni-view",{staticClass:"dinggou",staticStyle:{"font-size":"24rpx"}},[e("v-uni-view",[t._v(t._s(i.createDate))]),e("v-uni-view",{staticClass:"yuding"},[t._v(t._s(i.status))])],1)],1)],1)],1)})),e("u-loadmore",{attrs:{status:t.loadStatus[3],bgColor:"#f2f2f2"}})],2)],1)],1)],1)],1)],1)},n=[]},dbbf:function(t,i,e){var s=e("24fb");i=s(!1),i.push([t.i,'@charset "UTF-8";\r\n/**\r\n * 下方引入的为uView UI的集成样式文件，为scss预处理器，其中包含了一些"u-"开头的自定义变量\r\n * uView自定义的css类名和scss变量，均以"u-"开头，不会造成冲突，请放心使用 \r\n */.order[data-v-6e32f68d]{width:%?710?%;background-color:#fff;margin:%?20?% auto;-webkit-border-radius:%?20?%;border-radius:%?20?%;-webkit-box-sizing:border-box;box-sizing:border-box;padding:%?15?%;font-size:%?28?%}.order .item[data-v-6e32f68d]{display:-webkit-box;display:-webkit-flex;display:flex;margin:%?8?% 0 0}.order .item .left[data-v-6e32f68d]{margin-right:%?20?%}.order .item .left uni-image[data-v-6e32f68d]{width:%?200?%;height:%?130?%;-webkit-border-radius:%?10?%;border-radius:%?10?%}.order .item .content[data-v-6e32f68d]{display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;flex-direction:column;-webkit-box-pack:justify;-webkit-justify-content:space-between;justify-content:space-between;margin:%?10?% 0 0 %?20?%;width:%?504?%}.order .item .content .dinggou[data-v-6e32f68d]{display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;flex-direction:row;-webkit-box-pack:justify;-webkit-justify-content:space-between;justify-content:space-between}.order .item .content .dinggou .jiage[data-v-6e32f68d]{display:-webkit-box;display:-webkit-flex;display:flex}.order .item .content .dinggou .yuding[data-v-6e32f68d]{display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;flex-direction:column;margin:0 %?20?% %?10?% 0;border:%?1?% solid #ff850d;-webkit-border-radius:%?14?%;border-radius:%?14?%;font-size:%?24?%;width:%?120?%;text-align:center;color:#ff850d}.wrap[data-v-6e32f68d]{display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;flex-direction:column;height:calc(100vh - var(--window-top));width:100%}.swiper-box[data-v-6e32f68d]{-webkit-box-flex:1;-webkit-flex:1;flex:1}.swiper-item[data-v-6e32f68d]{height:100%}',""]),t.exports=i}}]);