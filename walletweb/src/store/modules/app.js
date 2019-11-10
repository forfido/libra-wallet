import { set, toggle } from "@/utils/vuex";
import MenuJson from "@/assets/sampleData/menu.json";

const state = {
  drawer: null,
  menuList: MenuJson
};
const getters = {};
const mutations = {
  setDrawer: set("drawer"),
  toggleDrawer: toggle("drawer")
};
const actions = {};

export default {
  state,
  getters,
  mutations,
  actions
};
