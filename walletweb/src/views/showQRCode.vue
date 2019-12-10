<template>
  <v-sheet height="400">
    <qrcode-vue :value="QRCodeValue" :size="400" level="H"></qrcode-vue>
  </v-sheet>
</template>

<script>
  import QrcodeVue from "qrcode.vue";
  import Constants from "@/constants";

  export default {
    data: () => ({
      QRCodeValue: "google.com"
      // Constants.FRONTPOINT + "/SendLibra"
    }),
    components: {
      QrcodeVue,
    },
  }
</script>