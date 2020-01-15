<template>
    <v-card
            class="mx-auto"
            max-width="500"
    >
        <v-card-title class="title font-weight-regular justify-space-between">
            <span><v-icon>fa-user-plus</v-icon> {{ currentTitle }}</span>
            <v-avatar
                    color="primary lighten-2"
                    class="subheading white--text"
                    size="24"
                    v-text="step"
            ></v-avatar>
        </v-card-title>

        <v-window v-model="step">
            <v-window-item :value="1">
                <v-card-text>
                    <v-text-field
                            prepend-icon="person"
                            label="UserName"
                            v-model="name"
                            required
                    ></v-text-field>
                    <v-text-field
                            prepend-icon="email"
                            label="Email"
                            v-model="email"
                            required
                    ></v-text-field>
                    <span v-if="isValue" class="caption red--text text--darken-1">
                        Please input a name, email your account
                     </span>
                </v-card-text>
            </v-window-item>

            <v-window-item :value="2">
                <v-card-text>
                    <v-text-field
                            prepend-icon="lock"
                            label="Password"
                            type="password"
                            v-model="password"
                    ></v-text-field>
                    <v-text-field
                            prepend-icon="lock"
                            label="Confirm Password"
                            type="password"
                            v-model="confirmPassword"
                    ></v-text-field>
                     <span v-if="isValue" class="caption red--text text--darken-1">
                        Please enter a password for your account
                     </span>
                </v-card-text>
            </v-window-item>

            <v-window-item :value="3">
                <div class="pa-4 text-center">
                    <h3 class="title green--text font-weight-light mb-2">Welcome to {{name}}</h3>
                    <span class="caption green--text">Thanks for signing up!</span>
                </div>
            </v-window-item>
        </v-window>

        <v-divider></v-divider>

        <v-card-actions>
            <v-btn
                    :disabled="step === 1"
                    text
                    @click="step--"
            >
                Back
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    :disabled="step === 4"
                    color="primary"
                    depressed
                    @click="NextAction({name, email, password, confirmPassword})"
            >
                Next
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import axios from "axios";

    export default {
        data: () => ({
            step: 1,
            name: "",
            email: "",
            password: "",
            confirmPassword: "",
            isValue: false
        }),

        computed: {
            currentTitle () {
                switch (this.step) {
                    case 1: return 'Sign-up'
                    case 2: return 'Create a password'
                    default: return 'Account created'
                }
            },
        },
        methods: {
            // vee-validate 라이브러리 + Mixin 사용 할 예정
            NextAction: function(payload) {
                if (this.step == 1)
                {
                    if (payload.name == "" || payload.email == "") {
                        this.isValue = true;
                        return;
                    }
                    else {
                        this.isValue = false;
                        this.step++;
                        return;
                    }
                }
                if (this.step == 2)
                {
                    if (payload.password == "" && payload.confirmPassword == "") {
                        this.isValue = true;
                        return;
                    }
                    else if (payload.password !== payload.confirmPassword) {
                        this.isValue = true;
                        return;
                    }
                    else {
                        this.isValue = false;
                        let httpaxios = axios.create({
                            baseURL: this.$const.ENDPOINT,
                            timeout: this.$const.HTTPTIMEOUT,
                        });

                        httpaxios
                            .post("/auth/signUp", {
                                name: payload.name,
                                email: payload.email,
                                password: payload.password
                            })
                            .then(res => {
                                console.log(res);
                                this.step++;
                            })
                            .catch(err => {
                                alert(err);
                            })

                        return;
                    }
                }

                if (this.step == 3) {
                    this.$router.replace("/Login");
                }
            },
        }
    }
</script>