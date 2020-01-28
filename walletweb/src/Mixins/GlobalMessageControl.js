export const GlobalMessageControl = {
    data() {
        return {
        };
    },
    created() {
    },
    computed: {
    },
    methods: {
        showSuccessAlert(successMessage) {
            this.$store.dispatch("app/showSuccessAlert", successMessage);
        },
        showErrorAlert(errorMessage) {
            this._vm.$store.dispatch("app/showErrorAlert", errorMessage);
        },
        hideAlert() {
            this._vm.$store.dispatch("app/hideAlert");
        },
    }
};
