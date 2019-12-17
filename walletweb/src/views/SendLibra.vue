<template>
  <v-card
          color="blue lighten-2"
          dark
  >
    <v-card-title class="headline blue lighten-3">
      Send
    </v-card-title>
    <v-card-text>
      My Libra Balance {{balance}}
    </v-card-text>

    <v-card-text>
      <v-text-field
        label="Send Amount"
      ></v-text-field>
    </v-card-text>

    <v-card-text>
      <v-autocomplete
              v-model="model"
              :items="items"
              :loading="isLoading"
              :search-input.sync="search"
              color="white"
              hide-no-data
              hide-selected
              item-text="email"
              item-value="API"
              label="Receiver"
              placeholder="Start typing to Search"
              prepend-icon="mdi-database-search"
              return-object
      ></v-autocomplete>
    </v-card-text>
    <v-divider></v-divider>
    <v-expand-transition>
      <v-list v-if="model" class="red lighten-3">
        <v-list-tile
                v-for="(field, i) in fields"
                :key="i"
        >
          <v-list-tile-content>
            <v-list-tile-title v-text="field.value"></v-list-tile-title>
            <v-list-tile-sub-title v-text="field.key"></v-list-tile-sub-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-expand-transition>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
              :disabled="!model"
              color="grey darken-3"
              @click="model = null"
      >
        Clear
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
      emailLimit: 60,
      contents: [],
      isLoading: false,
      model: null,
      search: null,
    }),
    created() {
      this.$store.dispatch("libraAccount/getBalance");
    },
    computed: {
      ...libraAccountHelper.mapState(['balance']),

      fields () {
        if (!this.model) {
          return [];
        }

        return Object.keys(this.model).map(key => {
          return {
            key,
            value: this.model[key] || 'n/a',
          }
        });
      },
      items () {
        return this.contents.map(entry => {
          const email = entry.email.length > this.emailLimit
                  ? entry.email.slice(0, this.emailLimit) + '...'
                  : entry.email;

          return Object.assign({}, entry, { email })
        })
      },
    },

    watch: {
      search (val) {
        // Items have already been loaded
        if (this.items.length > 0) {
          return;
        }

        // Items have already been requested
        if (this.isLoading) {
          return;
        }

        this.isLoading = true;

        // Lazily load input items
        fetch('http://localhost:8090/user/search/email/' + val)
                .then(res => res.json())
                .then(res => {
                  const { success, contents } = res;
                  this.contents = contents;
                })
                .catch(err => {
                  console.log(err);
                })
                .finally(() => (this.isLoading = false))
      },
    },
  }
</script>