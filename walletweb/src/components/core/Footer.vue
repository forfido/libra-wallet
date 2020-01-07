<template>
  <v-footer app fixed>
    <span>&copy; {{tittle}}-Payletter</span>
  </v-footer>
</template>

<script>
  export default {
    data: () => ({
      tittle: process.env.VUE_APP_TITTLE,
    })
  }
</script>