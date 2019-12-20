<template>
  <v-container fluid>
    <v-card
            color="yellow darken-4"
            dark
    >
      <v-card-title class="headline yellow darken-3">
        <v-icon>waves</v-icon>
        <b class="font-italic">&nbsp;{{title}}</b>
      </v-card-title>

      <v-card-text>
        My Libra Balance {{balance}}
      </v-card-text>

      <v-card-text>
        <v-text-field
          label="Send Amount"
          type="number"
          v-model="amount"
        ></v-text-field>
      </v-card-text>

      <v-card-text>
        <v-text-field
          label="Receiver Address"
          type="text"
          v-model="receiverAddress"
        ></v-text-field>
      </v-card-text>

      <v-card-text>
        <v-autocomplete
                v-model="model"
                :items="items"
                :loading="isLoading"
                :search-input.sync="search"
                color="white"
                @change="chooseId()"
                hide-no-data
                hide-selected
                item-text="email"
                item-value="API"
                label="Receiver"
                placeholder="Start typing to Search"
                prepend-icon="mdi-database-search"
                return-object
        ></v-autocomplete>
      </v-card-text>
      <v-divider></v-divider>
      <v-expand-transition>
        <v-list v-if="model" class="blue lighten-3">
          <v-list-tile-content>
            <v-avatar
              size="50px"
            >
              <v-img
                :src="model['imageUrl']"
                contain
                height="100px"
              ></v-img>
            </v-avatar>
          </v-list-tile-content>
          <v-list-tile
                  v-for="(field, i) in fields"
                  :key="i"
          >
            <v-list-tile-content>
              <v-list-tile-title v-text="field.value"></v-list-tile-title>
              <v-list-tile-sub-title v-text="field.key"></v-list-tile-sub-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-expand-transition>
      <v-card-actions>

        <v-btn
          color="grey darken-3"
          @click="clearSet()"
        >
          Clear
          <v-icon right>mdi-close-circle</v-icon>
        </v-btn>

        <v-spacer></v-spacer>
        <v-btn
                :disabled="!model || amount <= 0"
                color="blue"
                @click="sendLibra()"
                :loading="waitDialog"
        >
          Send LIB
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

  import Constants from "@/constants";
  import axios from "axios";

  const libraAccountHelper = createNamespacedHelpers("libraAccount");

  const httpaxios = axios.create({
    baseURL: Constants.ENDPOINT,
    timeout: Constants.HTTPTIMEOUT,
    headers: authHeader()
  });

  export default {


    mixins: [CommonViews],
    data: () => ({
      amount:0,
      receiverAddress: '',
      emailLimit: 60,
      contents: [],
      isLoading: false,
      model: null,
      search: null,
      waitDialog: false
    }),
    created() {
      this.$store.dispatch("libraAccount/getBalance");
    },
    computed: {
      ...libraAccountHelper.mapState(['balance']),

      fields () {
        if (!this.model) {
          return [];
        }

        return Object.keys(this.model).map(key => {
          return {
            key,
            value: this.model[key] || 'n/a',
          }
        }).filter(x => x.key == 'name');
      },
      items () {
        return this.contents.map(entry => {
          const email = entry.email.length > this.emailLimit
                  ? entry.email.slice(0, this.emailLimit) + '...'
                  : entry.email;

          return Object.assign({}, entry, { email })
        })
      },
    },
    methods:{
      clearSet() {
        this.amount = 0;
        this.receiverAddress = '';
        this.model = null;
        this.contents = [];
      },

      sendLibra() {
        this.waitDialog = true;

        httpaxios
          .post("libra/transfer/", {
            libraAddress: this.receiverAddress,
            amount: this.amount
          })
          .then(res => {
            console.log(res);
          })
          .catch(err => {
            console.log(err.response);
            /* 자기자신에게 전송했을 때 실패 응답 메세지 셈플
            err.response.data 에 해당하는 값
            {
              "timestamp": "2019-12-20T14:02:20.000+0000",
              "status": 416,
              "error": "Requested range not satisfiable",
              "message": "Not available transfer to yourself",
              "path": "/libra/transfer"
            }
             */
          })
          .finally(() => {
            setTimeout(() => {
              this.waitDialog = false;
              this.$router.push("/Home");
            }, 1000);
          })
      },

      chooseId() {
        this.receiverAddress =  this.model['libraAddress'].libraAddressToString;
      }
    },

    watch: {
      search (val) {
        // Items have already been loaded
        if (this.items.length > 0) {
          return;
        }

        // Items have already been requested
        if (this.isLoading) {
          return;
        }

        this.isLoading = true;

        httpaxios
          .get("user/search/email/"+ val)
          .then(res => {
            this.contents = res.data.contents;
          })
          .catch(err => {
            console.log(err);
          })
          .finally(() => (this.isLoading = false));

      },
    },
  }
</script>