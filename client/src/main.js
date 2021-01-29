import Vue from 'vue'

import 'normalize.css/normalize.css'

import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
import VCharts from 'v-charts'

import '@/styles/index.scss' // global css

import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

import '@/icons' // icon
import '@/permission' // permission control

Vue.use(Element, { locale })
Vue.use(VCharts)
Vue.config.productionTip = false
Vue.prototype.$axios = axios
// 设置浏览器标题
Vue.directive('title', {
    inserted: function (el, binding) {
        document.title = el.dataset.title
    }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
