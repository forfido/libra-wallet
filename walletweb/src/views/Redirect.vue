<template>
</template>

<script>
    export default {
        data: () => ({
        }),

        created() {
            let token = this.$route.query.token;
            let fromLibraAddress = this.$route.query.fromLibraAddress;
            let error = this.$route.query.error;

            // OAuth 유입된 경우
            if (token) {
                localStorage.removeItem(this.$const.ACCESS_TOKEN);
                localStorage.setItem(this.$const.ACCESS_TOKEN, token);
                this.$store.dispatch("auth/CheckAuthState");

                this.$router.replace("/Home");
            }

            // QRCode Scan 유입된 경우
            if (fromLibraAddress) {
                this.$router.push({ name: 'SendLibra', params: {fromLibraAddress: fromLibraAddress}})
            }
        },
        props: {
            source: String
        },
        computed: {
        },
        methods: {
        }
    };
</script>