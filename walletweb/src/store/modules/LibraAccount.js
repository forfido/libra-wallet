import axios from "axios";
import {authHeader} from "@/utils/authHeader";
import Constants from "@/constants";

const state = {
  libraAddress: null,
  balance: 0,
  microBalance: 0,
  libraTransactions: [],
};

const getters = {
  myTransactions: state => {
    state.libraTransactions.forEach(function(libraTransaction) {
      let action = "";
      let balance = null;
      let microBalance = null;

      console.log(libraTransaction.from);
      console.log(Constants.LIBRAROOTADDRESS);

      if (libraTransaction.from === Constants.LIBRAROOTADDRESS)
        action = "mint";
      else if(libraTransaction.to === state.libraAddress)
        action = "recv";
      else
        action = "send";

      balance = (libraTransaction.value / Constants.MICORLIBRARATE);
      microBalance = libraTransaction.value;

      libraTransaction.action = action;
      libraTransaction.balance = balance;
      libraTransaction.microBalance = microBalance;
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
    state.microBalance =+ payload.addedMicroLibra;
    state.balance =+ (payload.addedMicroLibra / Constants.MICORLIBRARATE);
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
    let httpaxios = axios.create({
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
  /// --------------------------------------------------------
  // mint Method
  /// --------------------------------------------------------
  mint({commit}, payload) {
    let httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });

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
    let httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });

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
    let httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });

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
    let httpaxios = axios.create({
      baseURL: Constants.ENDPOINT,
      timeout: Constants.HTTPTIMEOUT,
      headers: authHeader()
    });

    httpaxios
      .get('/libra/Transactions')
      .then(res => {
        let TransactionList = res.data.contents.result;
        console.log(TransactionList);
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
