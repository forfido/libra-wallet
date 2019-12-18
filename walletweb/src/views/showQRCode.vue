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
                  color="purple darken-2"
          >
            <v-card-title class="headline purple darken-1">
              <v-icon>fa-qrcode</v-icon>
              <b class="font-italic">&nbsp;{{title}}</b>
            </v-card-title>
            <qrcode-vue :value="QRCodeValue" :size="400" level="H"></qrcode-vue>
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
    data: () => ({
      QRCodeValue: "google.com"
      // this.$const.FRONTPOINT + "/SendLibra"
    }),
    components: {
      QrcodeVue,
    },
  }
</script>