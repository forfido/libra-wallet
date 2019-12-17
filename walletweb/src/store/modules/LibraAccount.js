import axios from "axios";
import {authHeader} from "@/utils/authHeader";
import Constants from "@/constants";

const state = {
  libraAddress: null,
  balance: 0,
  microBalance: 0,
};
const getters = {};
const mutations = {
  setBalance : function (state, payload) {
    state.balance = payload.libra;
    state.microBalance = payload.libra * Constants.MICORLIBRARATE;
    state.libraAddress = payload.libraAddress;
  },
  setAddedLibra : function (state, payload) {
    state.microBalance = state.microBalance + payload.addedMicroLibra;
    state.balance = state.balance + (payload.addedMicroLibra / Constants.MICORLIBRARATE);
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
  },
  mint({commit}, payload) {
    const httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });
  
    httpaxios
      .post("libra/mint", payload)
      .then(res => {
        let addedMicroLibra = res.data.contents;
        console.log(addedMicroLibra);
        commit("setAddedLibra", addedMicroLibra);
      })
      .catch(err => {
        console.log(err);
        commit("setAddedLibra", 0);
      });
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
