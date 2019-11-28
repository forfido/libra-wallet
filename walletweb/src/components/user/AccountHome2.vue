<template>
    <v-card
            class="mx-auto"
            max-width="344"
    >
        <v-card-title>
            My Account
        </v-card-title>

        <v-card-subtitle>
             <v-layout>
                <v-flex xs12 sm6 md4 class="pb-2">
                    <v-avatar
                        size="100px"
                    >
                        <v-img
                            :src="require('@/assets/bigone.png')"
                            contain
                            height="200px"
                        ></v-img>
                    </v-avatar>
                </v-flex>

                <v-flex xs12 sm6 md4 class="pb-2">
                    <v-icon small :color="info">face</v-icon>
                    <span class="grey--text caption font-italic">&nbsp;{{UserName}}</span>
                    <v-spacer></v-spacer>
                    
                    <v-icon small :color="warning">email</v-icon>
                    <span class="grey--text caption font-italic">&nbsp;{{Email}}</span>
                    <v-spacer></v-spacer>

                    <v-icon small :color="success">money</v-icon>
                    <span class="grey--text caption font-italic">&nbsp;{{balence}} &nbsp; Libra</span>
                    <v-spacer></v-spacer>
                </v-flex>
             </v-layout>
        </v-card-subtitle>

        <v-card-actions>
            <v-btn text round>송금하기</v-btn>

            <v-btn
                    color="purple"
                    text
            >
                require
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn
                    icon
                    @click="show = !show"
            >
                <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
        </v-card-actions>

        <v-expand-transition>
            <div v-show="show">
                <v-divider></v-divider>

                <v-card-text>
                    I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
                </v-card-text>
            </div>
        </v-expand-transition>
    </v-card>
</template>

<script>
    export default {
        data: () => ({
            show: false,
            UserName: "Bigone",
            Email: "trium10@gmail.com",
            balence: "1000",

        }),
    }
</script>