<template>
  <v-container justify-center grid-list-xs fill-height >
    <v-layout row wrap >
      <v-flex xs12>
        <user-AccountHome />
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
    }),
    created() {
      this.$store.dispatch("user/GetUserInfo");
      this.$store.dispatch("libraAccount/getBalance");
    }
  }
</script>
