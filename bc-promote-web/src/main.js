import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store";
import ElementUI from 'element-ui'
import './plugins/element.js'
import 'element-ui/lib/theme-chalk/index.css'

import {postRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {initMenu} from "./utils/menuUtil";
import md5 from 'js-md5';

Vue.config.productionTip = false
Vue.use(ElementUI);
// 以插件的形式使用请求
Vue.prototype.postRequest=postRequest;
Vue.prototype.getRequest=getRequest;
Vue.prototype.putRequest=putRequest;
Vue.prototype.deleteRequest=deleteRequest;
Vue.prototype.$md5 = md5;

// 配置路由前置守卫
router.beforeEach((to, from, next)=>{
  if(to.path != '/' && window.sessionStorage.getItem('token')){
    initMenu(router, store);
    console.log("router", router.options.routes)
    next();
  }else{
    next();
  }
});

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
