<template>
  <v-container fluid>
    <v-row
            align="center"
            justify="center"
    >
      <v-col cols="12">
        <v-hover v-slot:default="{ hover }">
          <v-card
                  :elevation="hover ? 12 : 2"
                  class="mx-auto"
                  height="400"
                  max-width="400"
                  color="gray lighten-2"
          >
            <v-card-title class="headline gray lighten-1">
              <v-icon color="purple darken-2">fa-qrcode</v-icon>
              <b class="font-italic while--text">&nbsp;{{title}}</b>
            </v-card-title>

            <v-card-text>
              <qrcode-vue :value="qrCodeValue" :size="size"></qrcode-vue>
            </v-card-text>
          </v-card>
        </v-hover>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import { CommonViews } from "@/Mixins/CommonViews";
  import QrcodeVue from "qrcode.vue";

  export default {
    mixins: [CommonViews],
    components: {
      QrcodeVue,
    },
    data() {
      return {
        qrCodeValue: this.$const.FRONTPOINT + "/Redirect?fromLibraAddress=" + this.$store.state.libraAccount.libraAddress,
        size: 250,
      }
    },
    created() {
      this.$store.dispatch("libraAccount/getAccount");

      // Page reload시 vuex를 접근하지 못함. ( vue 생명주기 확인해야함. )
      if(!this.$store.state.libraAccount.libraAddress) {
        alert("다시 접속 하세요.")
        this.$router.replace("/Home");
      }
    },
  }
</script>