<template>
  <v-app id="inspire" dark>
    <div v-if="isLogin">
      <core-toolbar />

      <core-navigation />
    </div>
    <div>
      <core-routerview />

      <core-footer />
    </div>
  </v-app>
</template>


<script>
import { createNamespacedHelpers } from "vuex";
const authHelper = createNamespacedHelpers("auth");

export default {
  props: {
    source: String
  },
  created() {
    this.$store.dispatch("auth/CheckAuthState");
    this.$store.dispatch("app/hideAlert");
  },
  computed: {
    ...authHelper.mapState(["isLogin"]),
  },
};
</script>