<template>
  <v-container fluid>
    <v-card
      color="red lighten-2"
      dark
    >
      <v-card-title >
        <v-icon>fa-plus</v-icon>
        <b class="font-italic">&nbsp;{{title}}</b>
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
  </v-container>
</template>


<script>
  import { CommonViews } from "@/Mixins/CommonViews";

  export default {
    mixins: [CommonViews],
    data: () => ({
      amount: 0,
    }),
    created() {
    },
    methods: {
      MintLibra: function (payload) {
        this.$store.dispatch("libraAccount/mint", payload);
      }
    },
  }
</script>