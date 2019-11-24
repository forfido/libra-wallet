import axios from "@/utils/AxiosHandler";

const state = {
  balance: 0
};
const getters = {};
const mutations = {
  setBalance : function (state, payload) {
    state.balance = payload;
  }
};
const actions = {
  getBalance({ commit }) {
    axios
        .get("libra/balance")
        .then(res => {
          let balance = res.data.contents;

          commit("setBalance", balance);
        })
        .catch(err => {
          console.log(err);
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
