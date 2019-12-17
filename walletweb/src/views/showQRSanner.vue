<template>
  <v-sheet height="400">
    <qrcode-stream v-if="isShowingCamera" @init="onInit" @decode="onDecode" :size="400"></qrcode-stream>
  </v-sheet>
</template>

<script>
  import { QrcodeStream, QrcodeDropZone, QrcodeCapture } from "vue-qrcode-reader";

  export default {
    data: () => ({
      isShowingCamera: true,
    }),
    components: {
      QrcodeStream,
      QrcodeDropZone,
      QrcodeCapture
    },
    method: {
      // Funtion running when QRCodeSannner run
      onDecode: (decodedString) => {
        console.log(decodedString);
      },
      onInit: async (promise) => {
        try {
          await promise;
          console.log("init", promise);
        }
        catch(error) {
          console.log("init", error);

          if(error.name === 'DropImageFetchError') {
            //"can't process cross-origin image"
          }
          else if(error.name === 'DropImageDecodeError') {
            //"drag-and-dropped file is not of type image and can't be decoded"
          }
          else if(error.name === 'StreamApiNotSupportedError') {
            //"this browser has no Stream API support"
          }
          else if(error.name === 'InsecureContextError') {
            //"camera access is only permitted in secure context. Use HTTPS or localhost rather than HTTP."
          }
          else {
            //"notdefined"
          }
        }
        finally {
          //"notdefined"
        }
      },
    }
  }
</script>