<template>
    <v-card
            class="mx-auto"
            max-width="400"
    >
        <v-system-bar light color="grey darken-2">
            <v-card-title>
                <b>UserInfo</b>
            </v-card-title>
        </v-system-bar>

        <v-card-subtitle>
             <v-layout>
                <v-flex xs12 sm4 md4 class="pb-2">
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

                <v-flex xs12 sm8 md4 class="pb-2">
                    <v-icon color="info">face</v-icon>
                    <b class="grey--text font-italic">&nbsp;{{UserName}}</b>
                    <v-spacer></v-spacer>
                    <v-icon color="warning">email</v-icon>
                    <b class="grey--text font-italic">&nbsp;{{Email}}</b>
                    <v-spacer></v-spacer>
                    <v-icon color="success">money</v-icon>
                    <b class="grey--text font-italic">&nbsp;{{balence}} &nbsp; Libra</b>
                    <v-spacer></v-spacer>
                </v-flex>
             </v-layout>
        </v-card-subtitle>


        <v-card-actions>
            <v-row class="flex-column ma-0 fill-height" justify="center">
                <v-col class="px-0">
                    <!-- // Create Account -->
                    <v-tooltip bottom>
                        <template v-slot:activator="{ on }">
                            <v-btn v-on="on" large icon color="red lighten-2" @click="CreateAccount()">
                                <v-icon>fa-plus</v-icon>
                            </v-btn>
                        </template>
                        <span small>CreateAccount</span>
                    </v-tooltip>
                </v-col>

                <v-col class="px-0">
                    <!-- // Send Libra -->
                    <v-tooltip bottom>
                        <template v-slot:activator="{ on }">
                            <v-btn v-on="on" large icon color="yellow darken-4" @click="SendLibra()">
                                <v-icon>waves</v-icon>
                            </v-btn>
                        </template>
                        <span small>SendLibra</span>
                    </v-tooltip>
                </v-col>

                <v-col class="px-0">
                    <!-- // Show Libra History -->
                    <v-tooltip bottom>
                        <template v-slot:activator="{ on }">
                            <v-btn v-on="on" large icon color="blue" @click="LibraHistory()">
                                <v-icon>history</v-icon>
                            </v-btn>
                        </template>
                        <span small>LibraHistory</span>
                    </v-tooltip>
                </v-col>

                <v-col class="px-0">
                    <!-- // Go Smart Contract -->
                    <v-tooltip bottom>
                        <template v-slot:activator="{ on }">
                            <v-btn v-on="on" large icon color="green" @click="SmartContract()">
                                <v-icon>fa-handshake</v-icon>
                            </v-btn>
                        </template>
                        <span small>SmartContract</span>
                    </v-tooltip>
                </v-col>

                <v-col class="px-0">
                    <!-- // Select Account -->
                    <v-tooltip bottom>
                        <template v-slot:activator="{ on }">
                            <v-btn v-on="on" large icon color="teal accent-4" @click="SelectAccount()">
                                <v-icon>account_tree</v-icon>
                            </v-btn>
                        </template>
                        <span small>SelectAccount</span>
                    </v-tooltip>
                </v-col>
            </v-row>

            <v-spacer></v-spacer>

            <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                    <v-btn v-on="on" icon @click="showQRCode()" color="purple darken-2">
                        <v-icon>fa-qrcode</v-icon>
                    </v-btn>
                </template>
                <span small>showQRCode</span>
            </v-tooltip>
            
            <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                    <v-btn v-on="on" icon @click="showQRSanner()" color="purple darken-3">
                        <v-icon>linked_camera</v-icon>
                    </v-btn>
                </template>
                <span small>showQRSanner</span>
            </v-tooltip>

        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        data: () => ({
            UserName:  "Bigone",
            Email: "trium10@gmail.com",
            balence: "1000",
        }),
        methods: {
            // CreateAccount
            CreateAccount: () => {
                this.$router.push("/CreateAccount");
            },
            // SendLibra
            SendLibra: () => {
                this.$router.push("/SendLibra");
            },
            // LibraHistory
            LibraHistory: () => {
                this.$router.push("/LibraHistory");
            },
            // SmartContract
            SmartContract: () => {
                this.$router.push("/SmartContract");
            },
            // SelectAccount
            SelectAccount: () => {
                this.$router.push("/SelectAccount");
            },
            // showQRCode
            showQRCode: () => {
                this.$router.push("/showQRCode");
            },
            // showQRSanner
            showQRSanner: () => {
                this.$router.push("/showQRSanner");
            }
        },
        
    }
</script>