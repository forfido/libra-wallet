
<script>
  import Constants from "@/constants";

  export default {
    mounted() {
      let token = this.$route.query.token;
      let error = this.$route.query.error;
        if(token) {
            localStorage.setItem(Constants.ACCESS_TOKEN, token);

            this.$router.replace("/Home");
        } else {
            this.$router.replace("/Login");
        }
    },

    data: () => ({
    }),

    props: {
      source: String
    },
    computed: {
    },
    methods: {
    }
  };
</script>