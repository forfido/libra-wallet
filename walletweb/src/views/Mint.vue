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
          :loading="waitDialog"
        >
          Mint
          <v-icon right>mdi-close-circle</v-icon>
        </v-btn>


        <v-dialog
          v-model="waitDialog"
          hide-overlay
          persistent
          width="300"
        >
          <v-card
            color="primary"
            dark
          >
            <v-card-text>
              Please stand by
              <v-progress-linear
                indeterminate
                color="white"
                class="mb-0"
              ></v-progress-linear>
            </v-card-text>
          </v-card>
        </v-dialog>


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
      waitDialog: false
    }),
    created() {
    },
    methods: {
      MintLibra: function (payload) {
        this.waitDialog = true;
        this.$store.dispatch("libraAccount/mint", payload);

        setTimeout(() => {
          this.waitDialog = false;
          this.$router.push("/Home");
        }, 2000);
      }
    },
  }
</script>