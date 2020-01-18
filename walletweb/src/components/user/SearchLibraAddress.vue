<template>
    <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="340">
            <template v-slot:activator="{ on }">
                <v-btn v-on="on" dark icon color="green" @click="initMethod()">
                    <v-icon>fa-search</v-icon>
                </v-btn>
            </template>

            <!--Search Address-->
            <v-card>
                <v-card-title class="headline font-weight-regular blue-grey white--text">Search Address</v-card-title>
                <v-card-text>
                    <v-subheader class="pa-0">Whose account are you looking for?</v-subheader>
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
                            l-icon="mdi-database-search"
                            return-object
                    ></v-autocomplete>
                </v-card-text>
                <v-divider></v-divider>
                <v-expand-transition>
                    <v-list v-if="model" class="grey blue--text darken-2">

                        <v-list-tile-content>
                            <v-avatar
                                    size="50px"
                            >
                                <v-img
                                        :src="model['imageUrl']"
                                        contain
                                        height="100px"
                                ></v-img>
                            </v-avatar>
                        </v-list-tile-content>
                        <v-list-tile
                                v-for="(field, i) in fields"
                                :key="i"
                        >
                            <v-list-tile-content>
                                <v-list-tile-title v-text="field.value"></v-list-tile-title>
                                <v-list-tile-sub-title v-text="field.key"></v-list-tile-sub-title>
                            </v-list-tile-content>
                            <v-icon
                                    color="success"
                                    @click="SelectLibraAddress()"
                            >mdi-check-outline</v-icon>
                        </v-list-tile>


                    </v-list>
                </v-expand-transition>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
    import axios from "axios";
    import { authHeader } from "@/utils/authHeader";

    export default {
        data () {
            return {
                dialog: false,
                model: null,
                emailLimit: 60,
                contents: [],
                isLoading: false,
                search: null,
            }
        },
        computed: {
            fields () {
                if (!this.model) {
                    return [];
                }
                return Object.keys(this.model).map(key => {
                    return {
                        key,
                        value: this.model[key] || 'n/a',
                    }
                }).filter(x => x.key == 'name');
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

        methods:{
            SelectLibraAddress() {
                this.$store.commit("libraAccount/setFromAccount", this.model['libraAddress'].libraAddressToString);
                this.dialog = !this.dialog;
            },
            initMethod() {
                this.model = null;
                this.contents = [];
                this.isLoading =  false;
            }
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

                let httpaxios = axios.create({
                    baseURL: this.$const.ENDPOINT,
                    timeout: this.$const.HTTPTIMEOUT,
                    headers: authHeader()
                });

                httpaxios
                    .get("user/search/email/"+ val)
                    .then(res => {
                        this.contents = res.data.contents;
                    })
                    .catch(err => {
                        console.log(err);
                    })
                    .finally(() => (this.isLoading = false));

            },
        },
    }
</script>