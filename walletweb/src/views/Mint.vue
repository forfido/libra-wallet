<template>
  <v-card
    color="blue lighten-2"
    dark
  >
    <v-card-title >
      Mint
    </v-card-title>

    <v-card-text>
      <v-text-field
        id="amount"
        label="Amount"
        name="amount"
        type="text"
        v-model="amount"
      ></v-text-field>
    </v-card-text>


    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        :disabled="amount <= 0"
        color="grey darken-3"
        @click="MintLibra({amount})"
      >
        Mint
        <v-icon right>mdi-close-circle</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>


<script>
  // import { createNamespacedHelpers } from "vuex";
  // import {authHeader} from "@/utils/authHeader";
  //
  // import axios from "axios";
  // import Constants from "@/constants";


  export default {
    data: () => ({
      amount: 0,

    }),
    created() {
    },
    methods: {
      MintLibra: function (payload) {
        this.$store.dispatch("libraAccount/mint", payload);
      //   const httpaxios = axios.create({
      //     baseURL: Constants.ENDPOINT,
      //     timeout: Constants.HTTPTIMEOUT,
      //     headers: authHeader()
      //   });
      //
      //   httpaxios
      //     .post("/libra/mint", {
      //       amount: payload.amount,
      //     })
      //     .then(res => {
      //       console.log(res.data.contents);
      //     })
      //     .catch(err => {
      //       console.log(err);
      //     });
      }
    },
  }
</script>