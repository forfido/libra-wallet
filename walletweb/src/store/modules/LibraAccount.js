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
  libraTransactions: [],
  balance: 0,
  microBalance: 0,
};

const getters = {
  myTransactions: state => {
    state.libraTransactions.forEach(function(libraTransaction) {
      let action = "";

      if(libraTransaction.to === state.libraAddress)
        action = "recv"
      else
        action = "send"

      libraTransaction.action = action;
    });

    return state.libraTransactions
  }
};

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
  setAccount : function (state, payload) {
    state.libraAddress = payload;
  },
  setTransactions : function (state, payload) {
    state.libraTransactions = payload;
  }
};
const actions = {
  /// --------------------------------------------------------
  // getBalance Method
  /// --------------------------------------------------------
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
  /// --------------------------------------------------------
  // mint Method
  /// --------------------------------------------------------
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
  /// --------------------------------------------------------
  // send Method
  /// --------------------------------------------------------
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
  },
  /// --------------------------------------------------------
  // getAccount Method
  /// --------------------------------------------------------
  getAccount({commit}) {
    httpaxios
        .get("libra/account")
        .then(res => {
          let accountAddress = res.data.contents;
          commit("setAccount", accountAddress);
        })
        .catch(err => {
          console.log(err);
        });
  },
  /// --------------------------------------------------------
  // getListTransaction Method
  /// --------------------------------------------------------
  getListTransaction({commit}) {
    httpaxios
      .get('/libra/Transactions')
      .then(res => {
        let TransactionList = res.data.contents.result;
        commit("setTransactions", TransactionList);
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
