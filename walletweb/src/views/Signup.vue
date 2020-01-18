<template>
  <v-container justify-center grid-list-xs fill-height >
    <v-layout row wrap>
      <v-flex xs12 >
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