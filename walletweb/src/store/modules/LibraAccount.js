import axios from "axios";
import {authHeader} from "@/utils/authHeader";

const state = {
  balance: 0,
  microBalance: 0,
};
const getters = {};
const mutations = {
  setBalance : function (state, payload) {
    state.balance = payload.libra;
    state.microBalance = payload.libraMicro;
  }
};
const actions = {
  getBalance({ commit }) {
      const httpaxios = axios.create({
          baseURL: Constants.ENDPOINT,
          timeout: Constants.HTTPTIMEOUT,
          headers: authHeader()
      });

      httpaxios
        .get("libra/balance")
        .then(res => {
          let balance = res.data.contents;

          commit("setBalance", balance);
        })
        .catch(err => {
          commit("setBalance", 0);
        });
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
