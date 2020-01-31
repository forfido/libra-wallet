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
            <div v-if="transaction.type == 'send'">
              <v-chip
                      class="white--text ml-0"
                      color="blue"
                      label
                      small
              >
                {{transaction.type}}
              </v-chip>
              {{transaction.version}}
            </div>
            <div v-else-if="transaction.type == 'mint'">
              <v-chip
                      class="white--text ml-0"
                      color="teal accent-4"
                      label
                      small
              >
                {{transaction.type}}
              </v-chip>
              {{transaction.version}}
            </div>
            <div v-else-if="transaction.type == 'recv'">
              <v-chip
                      class="white--text ml-0"
                      color="green"
                      label
                      small
              >
                {{transaction.type}}
              </v-chip>
              {{transaction.version}}
            </div>
            <div v-else>
              <v-chip
                      class="white--text ml-0"
                      color="red lighten-2"
                      label
                      small
              >
                {{transaction.type}}
              </v-chip>
              {{transaction.version}}
            </div>

            <v-col color="red" class="text-right" cols="5" v-text="transaction.from"></v-col>
            &nbsp;<v-icon>redo</v-icon>&nbsp;
            <v-col color="red" class="text-right" cols="5" v-text="transaction.to"></v-col>
            <br />
            <v-col cols="7" v-text="transaction.balance"></v-col>
            <v-col class="text-right" cols="5"> Libra </v-col>

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
      this.$store.dispatch("app/hideAlert");
    },
    computed: {
      timeline () {
        return this.$store.getters["libraAccount/myTransactions"].slice();
      },
    },
    methods: {
      getTransacionHistory () {
        this.$store.dispatch("libraAccount/getListTransaction");
      },
    },
  }
</script>