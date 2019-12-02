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
                    <v-btn large icon color="red lighten-2" @click="CreateAccount()">
                        <v-icon>fa-plus</v-icon>
                    </v-btn>
                </v-col>

                <v-col class="px-0">
                    <!-- // Send Libra -->
                    <v-btn large icon color="yellow darken-4" @click="SendLibra()">
                        <v-icon>waves</v-icon>
                    </v-btn>
                </v-col>

                <v-col class="px-0">
                    <!-- // Show Libra History -->
                    <v-btn large icon color="blue" @click="LibraHistory()">
                        <v-icon>history</v-icon>
                    </v-btn>
                </v-col>

                <v-col class="px-0">
                    <!-- // Go Smart Contract -->
                    <v-btn large icon color="green" @click="SmartContract()">
                        <v-icon>fa-handshake</v-icon>
                    </v-btn>
                </v-col>

                <v-col class="px-0">
                    <!-- // Select Account -->
                    <v-btn large icon color="teal accent-4" @click="SelectAccount()">
                        <v-icon>account_tree</v-icon>
                    </v-btn>
                </v-col>
            </v-row>

            <v-spacer></v-spacer>

            <v-btn icon @click="showQRCode = !showQRCode" color="purple darken-2">
                <v-icon>{{ showQRCode ? 'close' : 'fa-qrcode' }}</v-icon>
            </v-btn>

            <v-btn icon @click="showQRSanner = !showQRSanner" color="purple darken-3">
                <v-icon>{{ showQRSanner ? 'close' : 'linked_camera' }}</v-icon>
            </v-btn>


        </v-card-actions>

        <v-expand-transition>
            <div v-show="showQRCode">
                <v-divider></v-divider>

                <v-card-text>
                    <v-layout>
                        <v-flex xs12 sm12 md12 class="pb-2">
                            <v-avatar :size="380">
                                <qrcode-vue :value="'http://google.com'" :size="380" level="H"></qrcode-vue>
                            </v-avatar>
                        </v-flex>
                    </v-layout>
                    
                </v-card-text>
            </div>
        </v-expand-transition>
        <v-expand-transition>
            <div v-show="showQRSanner">
                <v-divider></v-divider>

                <v-card-text>
                    <qrcode-stream v-if="isShowingCamera" @init="onInit" @decode="onDecode" :size="300"></qrcode-stream>
                </v-card-text>
            </div>
        </v-expand-transition>
    </v-card>
</template>

<script>
    import QrcodeVue from "qrcode.vue"

    export default {
        data: () => ({
            showQRCode: false,
            showQRSanner: false,
            UserName:  "Bigone",
            Email: "trium10@gmail.com",
            balence: "1000",
            isShowingCamera: true,
        }),
        methods: {
            // CreateAccount
            CreateAccount: () => {
                alert("CreateAccount page");
            },
            // SendLibra
            SendLibra: () => {
                alert("SendLibra page");
            },
            // LibraHistory
            LibraHistory: () => {
                alert("LibraHistory page");
            },
            // SmartContract
            SmartContract: () => {
                alert("SmartContract page");
            },
            // SelectAccount
            SelectAccount: () => {
                alert("SelectAccount page");
            },
            // Funtion running when QRCodeSannner run
            onDecode: (decodedString) => {
                console.log(decodedString);
            },
            onInit: async (promise) => {
                try {
                    await promise;
                    console.log("init", promise);
                }
                catch(error) {
                    console.log("init", error);

                    if(error.name === 'DropImageFetchError') {
                        //"can't process cross-origin image"
                    }
                    else if(error.name === 'DropImageDecodeError') {
                        //"drag-and-dropped file is not of type image and can't be decoded"
                    }
                    else if(error.name === 'StreamApiNotSupportedError') {
                        //"this browser has no Stream API support"
                    }
                    else if(error.name === 'InsecureContextError') {
                        //"camera access is only permitted in secure context. Use HTTPS or localhost rather than HTTP."
                    }
                    else {
                        //"notdefined"
                    }
                }
                finally {
                    //"notdefined"
                }
            },
        },
        components: {
            QrcodeVue,
        },
    }
</script>