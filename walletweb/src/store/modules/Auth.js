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
  TryLogin({ commit }, payload) {
    axios
      .post("/auth/login", {
        email: payload.email,
        password: payload.password
      })
      .then(res => {
        localStorage.setItem(Constants.ACCESS_TOKEN, res.data.contents.accessToken);
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
    if (localStorage.getItem(Constants.ACCESS_TOKEN)) {
      commit("LoginSuccess");
    } else {
      commit("LogOut");
    }
  },
};

export default {
  state,
  getters,
  mutations,
  actions
};
