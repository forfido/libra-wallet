<template>
  <v-container grid-list-md text-center>
    <v-layout wrap>
      <v-sheet color="green darken-3">CardList</v-sheet>
      <v-flex xs12>
        <list-Cardlist />
      </v-flex>

      <v-flex xs12>
        <br />
        <v-divider></v-divider>
        <br />
      </v-flex>

      <v-sheet color="green darken-3">DataList</v-sheet>
      <v-flex xs12>
        <list-Datalist />
      </v-flex>

      <v-flex xs12>
        <br />
        <v-divider></v-divider>
        <br />
      </v-flex>
    </v-layout>
  </v-container>
</template>