import { set, toggle } from "@/utils/vuex";
import MenuJson from "@/assets/sampleData/navigation.json";

const state = {
  drawer: null,
  menuList: MenuJson,
  successAlert: false,
  errorAlert: false,
  globalMessage: null
};
const getters = {};
const mutations = {
  setDrawer: set("drawer"),
  toggleDrawer: toggle("drawer"),

  /// --------------------------------------------------------
  // show Global Success Alert
  /// --------------------------------------------------------
  showSuccessAlert(state, payload) {
    state.successAlert = true;
    state.errorAlert = false;
    state.globalMessage = payload;
  },

  /// --------------------------------------------------------
  // show Global Error Alert
  /// --------------------------------------------------------
  showErrorAlert(state, payload) {
    state.errorAlert = true;
    state.successAlert = false;
    state.globalMessage = payload;
  },

  /// --------------------------------------------------------
  // hide Global Alert
  /// --------------------------------------------------------
  hideAlert(state) {
    state.successAlert = false;
    state.errorAlert = false;
  },
};
const actions = {
  /// --------------------------------------------------------
  // show Global Success Alert
  /// --------------------------------------------------------
  showSuccessAlert({ commit }, payload) {
    commit("showSuccessAlert", payload);
  },

  /// --------------------------------------------------------
  // show Global Error Alert
  /// --------------------------------------------------------
  showErrorAlert({ commit }, payload) {
    commit("showErrorAlert", payload);
  },

  /// --------------------------------------------------------
  // hide Global Alert
  /// --------------------------------------------------------
  hideAlert({ commit }) {
    commit("hideAlert");
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
