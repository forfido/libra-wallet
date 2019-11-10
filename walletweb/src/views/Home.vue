<template>
  <v-container>
    <v-layout>
      <v-flex xs12 sm12 offset-sm6>
        <img src="../assets/logo.png" aspect-ratio="2.75" />
      </v-flex>
    </v-layout>
  </v-container>
</template>

<style>
img {
  width: 300px;
  text-align: center;
}
</style>

<script>
export default {
  data() {
    return {};
  },
  props: {
    source: String
  }
};
</script>
