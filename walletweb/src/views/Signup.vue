<template>
  <v-container grid-list-md text-center>
    <v-layout wrap>
      <v-flex xs12>
        <core-NewSignup />
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import { CommonViews } from "@/Mixins/CommonViews";
  export default {
    mixins: [CommonViews],
    data: () => ({
    }),
  }
</script>