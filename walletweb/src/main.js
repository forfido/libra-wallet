/* eslint-disable quotes */
/* eslint-disable semi */
import Vue from "vue";
// Commponent Resgiter
import "./components";
// Plugins Resgiter
import "./plugins";

// Application imnport
import App from "./App";
import router from "./router";
import store from "./store/store";
import Constants from "@/constants";
import VueQrcodeReader from "vue-qrcode-reader";

Vue.config.productionTip = false;
Vue.prototype.$const = Constants;
Vue.use(VueQrcodeReader);

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount("#app");
