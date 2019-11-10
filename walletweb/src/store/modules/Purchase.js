import PurchaseJson from "@/assets/sampleData/purchase.json";

const state = {
  Purchases: PurchaseJson,
  TableHeaders: [
    { text: "accountNo", align: "right", value: "accountid" },
    { text: "chargeNo", align: "right", value: "chargeNo" },
    { text: "ProdName", align: "center", value: "prodName" },
    { text: "price", align: "right", value: "price" },
    { text: "vat (%)", align: "right", value: "vat" }
  ]
};
const getters = {};
const mutations = {};
const actions = {};

export default {
  state,
  getters,
  mutations,
  actions
};
