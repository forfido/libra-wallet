<template>
  <v-layout row>
    <v-flex xs12 sm6 offset-sm3>
      <v-card>
        <v-toolbar color="indigo" dark>
          <v-toolbar-title>AccountInfo</v-toolbar-title>
        </v-toolbar>
        <v-list>
          <v-list-tile v-for="account in Accounts" :key="account.id">
            <v-list-tile-action></v-list-tile-action>

            <v-list-tile-content>
              <v-list-tile-title v-text="account.last_name + account.first_name"></v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-avatar>
              <img :src="account.avatar" />
            </v-list-tile-avatar>
          </v-list-tile>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
</template>



<script>
import { createNamespacedHelpers } from "vuex";
const AccountHelper = createNamespacedHelpers("account");

export default {
  data() {
    return {};
  },
  computed: {
    ...AccountHelper.mapState(["Accounts"])
  }
};
</script>