// Lib imports
import Vue from "vue";
import Router from "vue-router";
import Meta from "vue-meta";
import axios from "axios";
import {authHeader} from "@/utils/authHeader";
import Constants from "@/constants";

import { createNamespacedHelpers } from "vuex";
const user = createNamespacedHelpers("user");

// Routes
import paths from "./paths";

// eslint-disable-next-line space-before-function-paren
function route(path, view, name) {
  return {
    name: name || view,
    path,
    component: resovle => import(`@/views/${view}.vue`).then(resovle)
  };
}

Vue.use(Router);

// Create a new router
const router = new Router({
  mode: "history",
  routes: paths
    .map(path => route(path.path, path.view, path.name))
    .concat([{ path: "*", redirect: "/Home" }]),

  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    }
    if (to.hash) {
      return { selector: to.hash };
    }
    return { x: 0, y: 0 };
  }
});

Vue.use(Meta);

// Login여부체크
// TODO : axios Vue Instance 적용
let loginCheck = function (next) {
  let httpaxios = axios.create({
    baseURL: Constants.ENDPOINT,
    timeout: Constants.HTTPTIMEOUT,
    headers: authHeader()
  });

  httpaxios
      .get("/user/me")
      .then(res => {
        next();
      })
      .catch(ex => {
        alert("로그인이 필요합니다.(ex:" + ex + ")");

        next({ path: "/Login" });
      });
};

// Router진입시(전체체크)
router.beforeResolve((to, from, next) => {
  if (to.path === "/Login" || to.path === "/Redirect") {
    if (from.path !== to.path) return next();
  } else {
    loginCheck(next);
  }
});

export default router;
