<template>
  <v-card
    color="blue lighten-2"
    dark
  >
    <v-card-title >
      Mint
    </v-card-title>

    <v-card-text>
      <v-text-field
        label="Amount"
      ></v-text-field>
    </v-card-text>


    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        :disabled="!model"
        color="grey darken-3"
        @click="model = null"
      >
        Mint
        <v-icon right>mdi-close-circle</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>


<script>
  import { createNamespacedHelpers } from "vuex";
  const libraAccountHelper = createNamespacedHelpers("libraAccount");

  export default {
    data: () => ({
      isLoading: false,
    }),
    created() {
    },
    methods: {

    },
  }
</script>