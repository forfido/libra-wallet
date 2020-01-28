<template>
  <v-container fluid>
    <v-card
            color="gray darken-2"
            dark
    >
      <v-card-title class="headline gray darken-3">
        <v-icon color="yellow lighten-2">waves</v-icon>
        <b class="font-italic while--text">&nbsp;{{title}}</b>
      </v-card-title>

      <v-card-text>
        <p>My Balance <b class="green--text">{{balance}}</b> Libra
          <v-btn v-on="on" icon @click="showQRSanner()" color="purple darken-3">
            <v-icon>linked_camera</v-icon>
          </v-btn>
        </p>
      </v-card-text>

      <v-card-text>
        <v-layout>
          <v-flex xs2 sm4 md4 class="pb-2">
            <v-btn dark icon color="green" @click="clearSet()">
              <v-icon>waves</v-icon>
            </v-btn>
          </v-flex>
          <v-flex xs10 sm4 md4 class="pb-2">
            <v-text-field
                    label="Send Amount"
                    type="number"
                    v-model="amount"
                    min="0"
            ></v-text-field>
          </v-flex>
        </v-layout>
      </v-card-text>

      <v-card-text>
        <v-layout>
          <v-flex xs2 sm4 md4 class="pb-2">
          <!--SearchLibraAdress-->
          <user-SearchLibraAddress />
          </v-flex>
          <v-flex xs10 sm4 md4 class="pb-2">
            <v-text-field
              label="Receiver Address"
              type="text"
              v-model="receiverAddress"
              disabled="true"
            ></v-text-field>
          </v-flex>
        </v-layout>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
                :disabled="amount <= 0 || !receiverAddress"
                color="blue"
                @click="sendLibra()"
                :loading="waitDialog"
        >
          Send
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
  import { createNamespacedHelpers } from "vuex";
  import { authHeader } from "@/utils/authHeader";

  import axios from "axios";

  const libraAccountHelper = createNamespacedHelpers("libraAccount");

  export default {
    mixins: [CommonViews],
    data: () => ({
      amount: null,
      emailLimit: 60,
      isLoading: false,
      waitDialog: false
    }),

    created() {
      this.$store.dispatch("libraAccount/getBalance");

      this.receiverAddress = this.$route.params.fromLibraAddress;

      this.$store.dispatch("app/hideAlert");
    },

    computed: {
      ...libraAccountHelper.mapState(['balance']),

      receiverAddress: {
        get() {
          return this.$store.state.libraAccount.fromLibraAddress;
        },
        set(val) {
          this.$store.commit("libraAccount/setFromAccount", val);
        }
      },
    },

    methods:{
      clearSet() {
        this.amount = 0;
        this.receiverAddress = '';
      },
      sendLibra() {
        this.waitDialog = true;

        this.$store.dispatch("libraAccount/send", {
          libraAddress: this.receiverAddress,
          amount: this.amount
        });

        setTimeout(() => {
          this.waitDialog = false;
        }, 2000);
      },
      // showQRSanner
      showQRSanner: function () {
        this.$router.push("/showQRSanner");
      }
    },
  }
</script>