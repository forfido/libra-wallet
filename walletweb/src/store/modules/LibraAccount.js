import axios from "axios";
import {authHeader} from "@/utils/authHeader";
import Constants from "@/constants";

const httpaxios = axios.create({
  baseURL: Constants.ENDPOINT,
  timeout: Constants.HTTPTIMEOUT,
  headers: authHeader()
});

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
  },
};
const actions = {
  getBalance({ commit }) {
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
    httpaxios
      .post("libra/mint", payload)
      .then(res => {
        let addedMicroLibra = res.data.contents;
        commit("setAddedLibra", addedMicroLibra);
      })
      .catch(err => {
        console.log(err);
        commit("setAddedLibra", 0);
      });
  },
  send({commit}, payload) {
    httpaxios
      .post("libra/transfer", payload)
      .then(res => {
        let balance = res.data.contents;
        commit("setBalance", balance);
      })
      .catch(err => {
        console.log(err);
      });
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
