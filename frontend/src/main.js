import Vue from 'vue'
import App from './App'
import uView from "../node_modules/uview-ui";
import './css/index.css'

Vue.use(uView);
Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
  ...App
})
app.$mount()
