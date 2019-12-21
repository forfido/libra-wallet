<template>
  <v-container style="max-width: 600px;">
    <v-timeline dense clipped>
      <v-timeline-item
              fill-dot
              class="white--text mb-12"
              color="orange"
              large
      >
        <template v-slot:icon>
          <v-icon>history</v-icon>
        </template>
            <v-btn
                    class="mx-0"
                    depressed
                    @click="getTransacionHistory"
            >
              Post
            </v-btn>
      </v-timeline-item>

      <v-timeline-item
              class="mb-6"
              hide-dot
      >
        <span>TODAY</span>
      </v-timeline-item>

      <v-slide-x-transition
              group
      >
        <v-timeline-item
                v-for="transaction in timeline"
                :key="transaction.sequenceNumber"
                class="mb-3"
                color="pink"
                small
        >
          <v-row justify="space-between">
            <v-chip
                    class="white--text ml-0"
                    color="purple"
                    label
                    small
            >
              {{transaction.action}}
            </v-chip>
            <v-col cols="7" v-text="transaction.value"></v-col>
            <v-col class="text-right" cols="5" v-text=""> Libra </v-col>
          </v-row>
        </v-timeline-item>
      </v-slide-x-transition>

      <v-timeline-item
              class="mb-4"
              small
      >
        <v-row justify="space-between">
          <v-col cols="7">
            Create Palibra
          </v-col>
          <v-col class="text-right" cols="5"> - Payletter</v-col>
        </v-row>
      </v-timeline-item>
    </v-timeline>
  </v-container>
</template>

<script>
  import { CommonViews } from "@/Mixins/CommonViews";

  export default {
    mixins: [CommonViews],
    data: () => ({
    }),
    created() {
      this.$store.dispatch("libraAccount/getAccount");
    },
    computed: {
      timeline () {
        return this.$store.getters["libraAccount/myTransactions"].slice().reverse();
      },
    },
    methods: {
      getTransacionHistory () {
        let payload = {
          module  : "account",
          action  : "txlist",
          address : "d1ecca696749316a1ebe71ecee2012ae004cd4427d0a043fcf700689924309b2",
          // address : this.$store.state.libraAccount.libraAddress,
          sort    : "asc",
        };
        this.$store.dispatch("libraAccount/getListTransaction", payload);
      },
    },
  }
</script>