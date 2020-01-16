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
  myTransactions: (state) => {
    state.libraTransactions.forEach(function(libraTransaction) {
      let type = "";
      let balance = (libraTransaction.value / Constants.MICORLIBRARATE);
      let microBalance = libraTransaction.value;
      let toAddr = libraTransaction.to;
      let fromAddr = libraTransaction.from;

      if (fromAddr === Constants.LIBRAROOTADDRESS)
      {
        type = "mint";

        libraTransaction.from = "minter";
        libraTransaction.to   = toAddr.substr( 0, 6 ) + "..." + toAddr.substr( toAddr.length-6, 6 );
      }
      else
      {
        (toAddr === state.libraAddress) ? type = "recv" : type = "send";

        libraTransaction.from = fromAddr.substr( 0, 6 ) + "..." + fromAddr.substr( fromAddr.length-6, 6 );
        libraTransaction.to   = toAddr.substr( 0, 6 ) + "..." + toAddr.substr( toAddr.length-6, 6 );
      }

      type = (libraTransaction.status == 1) ? "Fail" : type;

      libraTransaction.balance = balance;
      libraTransaction.microBalance = microBalance;
      libraTransaction.type = type;
    });

    return state.libraTransactions
  }
};

const mutations = {
  setBalance : function (state, payload) {
    state.balance = payload.libra;
    state.microBalance = payload.libra * this._vm.$const.MICORLIBRARATE;
    state.libraAddress = payload.libraAddress;
  },
  setAddedLibra : function (state, payload) {
    state.microBalance =+ payload.addedMicroLibra;
    state.balance =+ (payload.addedMicroLibra / this._vm.$const.MICORLIBRARATE);
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
      baseURL: this._vm.$const.ENDPOINT,
      timeout: this._vm.$const.HTTPTIMEOUT,
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
      baseURL: this._vm.$const.ENDPOINT,
      timeout: this._vm.$const.HTTPTIMEOUT,
      headers: authHeader()
    });

    httpaxios
      .post("libra/mint", payload)
      .then(res => {
        let addedMicroLibra = res.data.contents;
        commit("setAddedLibra", addedMicroLibra);

        this._vm.$vmrouter.push("/Home");
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
      baseURL: this._vm.$const.ENDPOINT,
      timeout: this._vm.$const.HTTPTIMEOUT,
      headers: authHeader()
    });

    httpaxios
      .post("libra/transfer", payload)
      .then(res => {
        let balance = res.data.contents;
        commit("setBalance", balance);

        this._vm.$vmrouter.push("/Home");
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
      baseURL: this._vm.$const.ENDPOINT,
      timeout: this._vm.$const.HTTPTIMEOUT,
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
      baseURL: this._vm.$const.ENDPOINT,
      timeout: this._vm.$const.HTTPTIMEOUT,
      headers: authHeader()
    });

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
