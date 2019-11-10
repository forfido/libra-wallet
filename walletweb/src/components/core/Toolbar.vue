
<template>
  <v-toolbar app fixed clipped-left>
    <v-toolbar-side-icon @click.stop="onClickBtn"></v-toolbar-side-icon>
    <v-toolbar-title>ExplainVue</v-toolbar-title>
  </v-toolbar>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const appHelper = createNamespacedHelpers("app");

export default {
  data() {
    return {};
  },
  methods: {
    ...appHelper.mapMutations(["setDrawer", "toggleDrawer"]),
    onClickBtn() {
      this.setDrawer(!this.$store.state.app.drawer);
    }
  }
};
</script>

