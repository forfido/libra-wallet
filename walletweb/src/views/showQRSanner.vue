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
                  height="100%"
                  max-width="500"
                  color="gray lighten-3"
          >
            <v-card-title class="headline gray lighten-2">
              <v-icon color="purple darken-1">linked_camera</v-icon>
              <b class="font-italic while--text">&nbsp;{{title}}</b>
            </v-card-title>

            <v-card-text>
              <qrcode-stream @decode="onDecode" @init="onInit" :size="size" />
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <p class="error">{{ error }}</p>

              <v-btn large @click="Cancel()">
                <b>Cancel</b>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-hover>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import { CommonViews } from "@/Mixins/CommonViews";
  import { QrcodeStream } from 'vue-qrcode-reader'

  export default {
    mixins: [CommonViews],
    components: { QrcodeStream },

    data () {
      return {
        result: '',
        error: '',
        size: 250,
      }
    },

    methods: {
      onDecode (result) {
        let urlParse = result.split("fromLibraAddress=");
        this.result = urlParse[1];

        this.$router.push({ name: 'SendLibra', params: {fromLibraAddress: result}});
      },

      async onInit (promise) {
        try {
          await promise
        } catch (error) {
          if (error.name === 'NotAllowedError') {
            this.error = "ERROR: you need to grant camera access permisson"
          } else if (error.name === 'NotFoundError') {
            this.error = "ERROR: no camera on this device"
          } else if (error.name === 'NotSupportedError') {
            this.error = "ERROR: secure context required (HTTPS, localhost)"
          } else if (error.name === 'NotReadableError') {
            this.error = "ERROR: is the camera already in use?"
          } else if (error.name === 'OverconstrainedError') {
            this.error = "ERROR: installed cameras are not suitable"
          } else if (error.name === 'StreamApiNotSupportedError') {
            this.error = "ERROR: Stream API is not supported in this browser"
          }
        }
      },
      Cancel: function () {
        this.$router.back();
      }
    }
  }
</script>

<style scoped>
  .error {
    font-weight: bold;
    color: red;
  }
</style>