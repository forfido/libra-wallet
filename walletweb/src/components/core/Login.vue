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
                <v-btn color="primary" block @click="TryLogin({email, password})">Login</v-btn>

                <v-btn color="primary" block href="http://localhost:8090/oauth2/authorize/google?redirect_uri=http://localhost:3000/Home">Google</v-btn>
                <v-btn color="primary" block @click="TryLogin({email, password})">FaceBook</v-btn>
              </v-card-actions>
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
    email: null,
    password: null
  }),
  props: {
    source: String
  },
  computed: {
    ...authHelper.mapState(["isLogin", "isLoginError"])
  },
  methods: {
    ...authHelper.mapActions(["TryLogin"])
  }
};
</script>