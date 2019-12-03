<template>
  <v-sheet height="500">
    <qrcode-vue :value="QRCodeValue" :size="500" level="H"></qrcode-vue>
  </v-sheet>
</template>

<script>
    import QrcodeVue from "qrcode.vue";
    import Constants from "@/constants";

    export default {
        data: () => ({
            QRCodeValue: Constants.FRONTPOINT + "/SendLibra"
        }),
        components: {
            QrcodeVue,
        },
    }
</script>