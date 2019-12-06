// Lib imports
import Vue from "vue";
import Router from "vue-router";
import Meta from "vue-meta";
import axios from "@/utils/AxiosHandler";

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
let loginCheck = (next) => {
  console.log(localStorage.getItem("accessToken"));
  console.log(axios);

  axios
    .get("/user/me")
    .then(() => {
      next();
    })
    .catch(() => {
      alert("로그인이 필요합니다.");

      next({ path: '/Login' })
    });
};

// Router진입시(전체체크)
router.beforeResolve((to, from, next) => {
  if (to.path === "/Login" || to.path === "/Redirect") {
    if (from.path !== to.path)
      return next();
  }
  else {
    loginCheck(next);
  }
});

export default router;
