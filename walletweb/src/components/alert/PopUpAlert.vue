<template>
    <v-layout >
        <v-flex xs12 sm12>
            <div class="md-5">
                <v-alert
                        :value="this.successAlert"
                        type="success"
                        dark
                        border="top"
                        icon="mdi-check"
                        transition="scale-transition"
                >
                    {{this.globalMessage}}
                </v-alert>
                <v-alert
                        :value="this.errorAlert"
                        type="error"
                        dark
                        border="top"
                        icon="error"
                        transition="scale-transition"
                >
                    {{this.globalMessage}}
                </v-alert>
            </div>
        </v-flex>
    </v-layout>
</template>

<script>
    import { createNamespacedHelpers } from "vuex";
    const appHelper = createNamespacedHelpers("app");

    export default {
        data: () => ({
        }),
        mounted() {
        },
        computed: {
            ...appHelper.mapState(["successAlert", "errorAlert", "globalMessage"])
        }
    }
</script>