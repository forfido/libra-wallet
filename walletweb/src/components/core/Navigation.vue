<template>
  <v-navigation-drawer v-model="inputDrawer" clipped fixed app>
    <v-list dense>
        <v-list-tile v-for="(menu, i) in menuList" :key="i" :to="menu.path" >      
        <v-list-tile-action>
          <v-icon>{{menu.icon}}</v-icon>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-list-tile-title v-text="menu.title" />
        </v-list-tile-content>
      </v-list-tile>
    </v-list>
  </v-navigation-drawer>
</template>


<script>
import { createNamespacedHelpers } from "vuex";
const appHelper = createNamespacedHelpers("app");

export default {
  data() {
    return {};
  },

  computed: {
    ...appHelper.mapState(["drawer", "menuList"]),

    inputDrawer: {
      get() {
        return this.$store.state.app.drawer;
      },
      set(val) {
        this.setDrawer(val);
      }
    }
  },

  methods: {
    ...appHelper.mapMutations(["setDrawer", "toggleDrawer"])
  }
};
</script>

