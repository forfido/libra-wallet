<template>
  <v-data-table :headers="TableHeaders" :items="Purchases" class="elevation-1">
    <template v-slot:items="props">
      <td class="text-xs-right">{{ props.item.accountid }}</td>
      <td class="text-xs-right">{{ props.item.chargeNo }}</td>
      <td class="text-xs-center">{{ props.item.prodName }}</td>
      <td class="text-xs-right">{{ props.item.price }}</td>
      <td class="text-xs-right">{{ props.item.vat }}</td>
    </template>
  </v-data-table>
</template>


<script>
import { createNamespacedHelpers } from "vuex";
const PurchaseHelper = createNamespacedHelpers("purchase");

export default {
  data() {
    return {};
  },
  computed: {
    ...PurchaseHelper.mapState(["TableHeaders", "Purchases"])
  }
};
</script>