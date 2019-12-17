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

Vue.config.productionTip = false;
Vue.prototype.$const = Constants;

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount("#app");
