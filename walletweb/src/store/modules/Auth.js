import axios from "@/utils/AxiosHandler";
import Constants from "@/constants";

const state = {
  isLogin: false,
  isLoginError: false
};
const getters = {
  GetIsLogin: state => { return state.isLogin; },
  GetIsLoginError: state => { return state.isLoginError; }
};
const mutations = {
  /// --------------------------------------------------------
  // Login Success
  /// --------------------------------------------------------
  LoginSuccess(state) {
    state.isLogin = true;
    state.isLoginError = false;
  },
  /// --------------------------------------------------------
  // Login Fail
  /// --------------------------------------------------------
  LoginFail(state) {
    state.isLogin = false;
    state.isLoginError = true;

  },
  /// --------------------------------------------------------
  // LogOut
  /// --------------------------------------------------------
  LogOut(state) {
    state.isLogin = false;
    state.isLoginError = false;
  }
};
const actions = {
  /// --------------------------------------------------------
  // TryLogin Method
  /// --------------------------------------------------------
  TryLogin({ commit }, SignUpObj) {
    axios
      .post("/api/login", {
        email: "eve.holt@reqres.in",
        password: "cityslicka"
      })
      .then(res => {
        let token = res.data.contents.tokenType + " " + res.data.contents.accessToken;
        localStorage.setItem(Constants.AUTHORIZTION, token);
        commit("LoginSuccess");
      })
      .catch(err => {
        console.log(err);
        commit("LoginFail");
      });
  },

  /// --------------------------------------------------------
  // LogOut Method
  // TODO : Login Page
  /// --------------------------------------------------------
  TryLogOut({ commit }) {
    commit("LogOut");
  },

  /// --------------------------------------------------------
  // Auth State Check
  /// --------------------------------------------------------
  CheckAuthState({ commit }) {
    if (localStorage.getItem(Constants.AUTHORIZTION)) {
      commit("LoginSuccess");
    } else {
      commit("LogOut");
    }
  }

};

export default {
  state,
  getters,
  mutations,
  actions
};
