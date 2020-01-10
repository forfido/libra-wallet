import axios from "axios"
import {authHeader} from "@/utils/authHeader"
import Constants from "@/constants";

const state = {
  User: null
};
const getters = {
  userImage: state => {
    if (state.User != null) {
      if (state.User.imageUrl == null || state.User.imageUrl == '') {
        return 'https://ui-avatars.com/api/?name=' + User.name;
      } else {
        return state.User.imageUrl;
      }
      
    }
  }
};
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
    let httpaxios = axios.create({
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
  },
  getUserInfoByEmail({commit}, paylaod) {
    let httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });

    httpaxios
        .get("user/search/email/"+ paylaod)
        .then(res => {
          this.contents = res.data.contents;
        })
        .catch(err => {
          console.log(err);
        })
  }

};

export default {
  state,
  getters,
  mutations,
  actions
};
