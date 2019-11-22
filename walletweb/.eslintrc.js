module.exports = {
    root: true,
    env: {
        node: true
    },
    extends: ["standard", "plugin:vue/recommended"],
    rules: {
        "no-console": process.env.NODE_ENV === "production" ? "error" : "off",
        "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
        'no-multiple-empty-lines': [ 'error', { 'max': 2, 'maxBOF': 1 }],
        'quotes': ['error', 'single'],
        "space-before-function-paren": [
            "error",
            {
                anonymous: "never",
                named: "never",
                asyncArrow: "always"
            }
        ],
        quotes: [2, "double", { avoidEscape: false }],
        semi: [2, "always"]
    },
    parserOptions: {
        parser: "babel-eslint"
    }
};
