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
  // LoginSuccess Method
  /// --------------------------------------------------------
  LoginSuccess({ commit }) {
    commit("LoginSuccess");
  },
  /// --------------------------------------------------------
  // LoginFail Method
  /// --------------------------------------------------------
  LoginFail({ commit }) {
    commit("LoginFail");
  },
  /// --------------------------------------------------------
  // LogOut Method
  /// --------------------------------------------------------
  TryLogOut({ commit }) {
    commit("LogOut");
  },

  /// --------------------------------------------------------
  // Auth State Check
  /// --------------------------------------------------------
  CheckAuthState({ commit }) {
    if (localStorage.getItem(this._vm.$const.ACCESS_TOKEN)) {
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
