<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Login</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    prepend-icon="person"
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
                <v-btn color="primary" large @click="TryLogin({email, password})">
                  <b>로그인</b>
                </v-btn>
              </v-card-actions>

              <v-system-bar light color="grey darken-2">
                <v-card-actions>
                  <v-btn color="deep-orange darken-4" round small href="http://localhost:8090/oauth2/authorize/google?redirect_uri=http://localhost:3000/Redirect">
                    <v-icon left>fab fa-google-plus</v-icon>
                    <v-spacer></v-spacer>
                    <span>Google Login</span>
                  </v-btn>
                  <v-btn color="light-blue accent-2" round small href="http://localhost:8090/oauth2/authorize/facebook?redirect_uri=http://localhost:3000/Redirect">
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

export default {
  data: () => ({
    email: "ahin@palibra.net",
    password: null
  }),
  mounted() {
  },
  props: {
    source: String
  },
  computed: {
    ...authHelper.mapState(["isLogin", "isLoginError"])
  },
  methods: {
    TryLogin: function (payload) {
      Promise.all([
        this.$store.dispatch("user/ClearUserInfo"),
        this.$store.dispatch("auth/TryLogin", payload)
      ]).then(
        this.$router.push("/Home")
      )
    }
  }
};
</script>