<template>
  <v-app id="inspire" dark>
    <div v-if="isLogin">
      <core-toolbar />

      <core-navigation />

      <core-routerview />

      <core-footer />
    </div>
    <div v-else>
      <core-login />
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
  computed: {
    ...authHelper.mapState(["isLogin"])
  }
};
</script>