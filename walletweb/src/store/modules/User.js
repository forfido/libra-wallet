import axios from "axios"
import {authHeader} from "@/utils/authHeader"
import Constants from "@/constants";

const state = {
  User: null
};
const getters = {};
const mutations = {
  SetUser(state, payLoad) {
    state.User = payLoad;
  },
  ClaerUser(state) {
    state.User = null;
  }
};
const actions = {
  /// --------------------------------------------------------
  // Get UserInfo Method
  /// --------------------------------------------------------
  GetUserInfo({ commit }) {
    const httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });

    httpaxios
      .get("/user/me")
      .then(res => {
        commit("SetUser", res.data.contents);
      })
      .catch(err => {
        console.log(err);
      });
  },
  ClearUserInfo({ commit }) {
    localStorage.removeItem(Constants.ACCESS_TOKEN);

    commit("ClaerUser");
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
