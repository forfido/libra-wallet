import axios from "@/utils/AxiosHandler";

const state = {
  LibraAccount: null
};
const getters = {};
const mutations = {};
const actions = {
  getBalance() {
    axios
        .get("libra/balance")
        .then(res => {
          // 성공시 토큰

          commit("LoginSuccess", SignUpObj);
        })
        .catch(err => {
          console.log(err);
          commit("LoginFail");
        });
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
