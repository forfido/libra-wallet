import axios from "@/utils/AxiosHandler";

const state = {
  User: null,
  isLogin: false,
  isLoginError: false
};
const getters = {};
const mutations = {
  // Login Success
  LoginSuccess(state, ObjUser) {
    state.isLogin = true;
    state.isLoginError = false;
    state.User = ObjUser;
  },
  // Login Fail
  LoginFail(state) {
    state.isLogin = false;
    state.isLoginError = true;
  },
  LogOut(state) {
    state.isLogin = false;
    state.isLoginError = false;
    state.User = null;
  }
};
const actions = {
  // ------------------
  // TryLogin Method
  // ------------------
  TryLogin({ commit }, SignUpObj) {
    console.log(SignUpObj.email, SignUpObj.password);
    axios
      .post("/api/login", {
        email: "eve.holt@reqres.in", // SignUpObj.email
        password: "cityslicka" // SignUpObj.password
      })
      .then(res => {
        console.log(res);
        let token = res.data.token;
        localStorage.setItem("Authentication", token);
        // 성공시 토큰
        axios
          .get("/api/users/2")
          .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
          });

        commit("LoginSuccess", SignUpObj);
      })
      .catch(err => {
        console.log(err);
        commit("LoginFail");
      });
  },
  // ------------------
  // LogOut Method
  // ------------------
  TryLogOut({ commit }) {
    commit("LogOut");
    //  TODO: Login Page
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
