
<script>
  import Constants from "@/constants";

  export default {
    created() {
      console.log('123123');
      let token = this.$route.query.token;
      let error = this.$route.query.error;

      if(token) {
        localStorage.setItem(Constants.ACCESS_TOKEN, token);
        this.$router.push("/Home");
      } else {
        this.$router.push("/Login");
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