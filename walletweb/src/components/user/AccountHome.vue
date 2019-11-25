<template>
    <v-container
      fill-height
      fluid
      grid-list-xl>
        <v-layout

          justify-center
          wrap
        >
            <v-flex
              xs12
              md8
            >
                <material-card
                  color="general"
                  title="My Account"
                  text=""
                >
                    <v-form>
                        <v-container py-0>
                            <v-layout wrap>
                                <v-flex
                                  xs12
                                  md4
                                >
                                    <v-text-field
                                      label="Balance : "
                                      disabled/>
                                </v-flex>
                                <div>{{balance}}</div>
                            </v-layout>
                        </v-container>
                    </v-form>
                </material-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>


<script>
import { createNamespacedHelpers } from "vuex";
const libraAccountHelper = createNamespacedHelpers("LibraAccount");

export default {
    data: () => ({
    }),
    props: {},
    computed: {
    },
    created() {
        this.$store.dispatch("LibraAccount/getBalance");
    },
    methods: {
        ...libraAccountHelper.mapState(["balance"]),
    }
};
</script>
