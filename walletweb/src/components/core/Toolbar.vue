
<template>
  <v-toolbar app fixed clipped-left>
    <v-toolbar-side-icon @click.stop="ShowToggle"></v-toolbar-side-icon>
    <v-toolbar-title>Palibra</v-toolbar-title>

    <v-spacer></v-spacer>

    <v-btn icon @click.stop="Logout">
        <v-icon>exit_to_app</v-icon>
    </v-btn>
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
    ShowToggle() {
      this.setDrawer(!this.$store.state.app.drawer);
    },
    Logout() {
      Promise.all([
        this.$store.dispatch("user/ClearUserInfo"),
        this.$store.dispatch("auth/TryLogOut")
      ]).then(() => {
        this.$router.push("/Login");
      });
    }
  }
};
</script>

