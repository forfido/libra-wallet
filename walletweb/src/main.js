/* eslint-disable quotes */
/* eslint-disable semi */
import Vue from "vue";

// Commponent Resgiter
import "./components";
// Plugins Resgiter
import "./plugins";

// Application imnport
import App from "./App";
import router from "@/router";
import store from "./store/store";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
