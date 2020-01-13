<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>
                  <v-icon>person</v-icon>
                  <b class="font-italic">&nbsp;{{this.$router.currentRoute.name}}</b>
                </v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    prepend-icon="email"
                    name="login"
                    label="Login"
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
                <v-btn color="primary" large @click="GoSignUp()">
                  <b>SignUp</b>
                </v-btn>
                <v-btn color="primary" large @click="TryLogin({email, password})">
                  <b>Login</b>
                </v-btn>
              </v-card-actions>

              <v-system-bar light color="grey darken-2">
                <v-card-actions>
                  <v-btn color="deep-orange darken-4" round small @click="GoogleSign()">
                    <v-icon left>fab fa-google-plus</v-icon>
                    <v-spacer></v-spacer>
                    <span>Google Login</span>
                  </v-btn>
                  <v-btn color="light-blue accent-2" round small @click="FacebookSign()">
                    <!--<v-icon left>assignment_ind</v-icon>-->
                    <v-icon left>fab fa-facebook</v-icon>
                    <v-spacer></v-spacer>
                    <span>FaceBook Login</span>
                  </v-btn>
                </v-card-actions>
              </v-system-bar>


            </v-card>

            <v-alert class="mt-3" :value="isLogin" type="success">로그인에 성공했습니다.</v-alert>
            <v-alert class="mt-3" :value="isLoginError" type="error">아이디와 비밀번호를 확인해주세요.</v-alert>
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
import Constants from "@/constants";


export default {
  data: () => ({
    email: null,
    password: null
  }),
  mounted() {
  },
  computed: {
    ...authHelper.mapState(["isLogin", "isLoginError"])
  },
  methods: {
    GoogleSign: function() {
      window.location.href = this.$const.ENDPOINT + "/oauth2/authorize/google?redirect_uri=" + this.$const.FRONTPOINT + "/Redirect";
    },
    FacebookSign: function() {
      window.location.href = this.$const.ENDPOINT + "/oauth2/authorize/facebook?redirect_uri=" + this.$const.FRONTPOINT + "/Redirect";
    },
    // 동기적으로 처리 하기 위해 vuex에서 처리 하지 않음.
    TryLogin: function (payload) {
      let httpaxios = axios.create({
        baseURL: this.$const.ENDPOINT,
        timeout: this.$const.HTTPTIMEOUT,
      });

      httpaxios
        .post("/auth/login", {
          email: payload.email,
          password: payload.password
        })
        .then(res => {
          localStorage.setItem(this.$const.ACCESS_TOKEN, res.data.contents.accessToken);
          this.$store.dispatch("auth/LoginSuccess");

          this.$router.replace("/Home");
        })
        .catch(err => {
          this.$store.dispatch("auth/LoginFail");
        })
    },
    GoSignUp: function () {
      this.$router.push("/Signup");
    }
  }
};
</script>