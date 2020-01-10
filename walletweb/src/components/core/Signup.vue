<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>
                  <v-icon>fa-user-plus</v-icon>
                  <b class="font-italic">&nbsp;{{this.$router.currentRoute.name}}</b>
                </v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    prepend-icon="person"
                    name="Name"
                    label="Name"
                    type="text"
                    v-model="name"
                  ></v-text-field>
                  <v-text-field
                    prepend-icon="email"
                    name="Email"
                    label="Email"
                    type="text"
                    v-model="email"
                  ></v-text-field>
                  <v-text-field
                    id="password"
                    prepend-icon="lock"
                    name="password"
                    label="Password"
                    type="password"
                    v-model="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" large @click="TrySignUp({name, email, password})">
                  <b>SignUp</b>
                </v-btn>
                <v-btn color="primary" large @click="Cancel()">
                  <b>Cancel</b>
                </v-btn>
              </v-card-actions>
            </v-card>

            <v-alert class="mt-3" :value="isValid" type="error">아이디와 비밀번호를 확인해주세요.</v-alert>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const authHelper = createNamespacedHelpers("auth");
import axios from "axios";

export default {
  data: () => ({
    name: null,
    email: null,
    password: null,
    isValid: false,
  }),
  mounted() {
  },
  computed: {
  },
  methods: {
    // 동기적으로 처리 하기 위해 vuex에서 처리 하지 않음.
    TrySignUp: function (payload) {
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
          this.$router.replace("/Login")
        })
        .catch(err => {
          alert(err);
        })
    },
    Cancel: function () {
      this.$router.back();
    }
  }
};
</script>