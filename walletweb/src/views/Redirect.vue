<template>
</template>

<script>
    export default {
        data: () => ({
        }),

        created() {
            let token = this.$route.query.token;
            let error = this.$route.query.error;

            if (token) {
                localStorage.removeItem(this.$const.ACCESS_TOKEN);
                localStorage.setItem(this.$const.ACCESS_TOKEN, token);
                this.$store.dispatch("auth/CheckAuthState");
            }

            this.$router.replace("/Home");
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