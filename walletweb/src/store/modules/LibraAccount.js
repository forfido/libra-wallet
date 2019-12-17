import axios from "axios";
import {authHeader} from "@/utils/authHeader";
import Constants from "@/constants";

const state = {
  libraAddress: null,
  balance: 0,
  microBalance: 0,
  addedLibra:0
};
const getters = {};
const mutations = {
  setBalance : function (state, payload) {
    state.balance = payload.libra;
    state.microBalance = payload.libraMicro;
    state.libraAddress = payload.libraAddress;
  },
  setAddedLibra : function (state, payload) {
    state.addedLibra = payload.addedLibra;
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
  mint({commit}, mintAmount) {
    const httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });
  
    httpaxios
      .post("libra/mint", { amount: mintAmount })
      .then(res => {
        let addedLibra = res.data.contents;
      
        commit("setAddedLibra", addedLibra);
      })
      .catch(err => {
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
