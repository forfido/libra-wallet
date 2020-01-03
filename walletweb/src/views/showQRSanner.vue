<template>
  <v-container fluid>
    <v-sheet height="100%">
      <qrcode-stream v-if="isShowingCamera" @init="onInit" @decode="onDecode" :size="400"></qrcode-stream>
    </v-sheet>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      isShowingCamera: true,
    }),
    method: {
      // Funtion running when QRCodeSannner run
      onDecode: (decodedString) => {
        alert(decodedString);
        //let SendUrlInfo = this.$const.FRONTPOINT + "/SendLibra?fromLibraAddress=" + decodedString;

        //this.$router.push(SendUrlInfo);
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