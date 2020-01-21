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
        'semi': [2, "always"],
        "template-curly-spacing" : "off",
        indent : "off",
        "space-before-function-paren": [
            "error",
            {
                anonymous: "never",
                named: "never",
                asyncArrow: "always"
            }
        ],
    },
    parserOptions: {
        parser: "babel-eslint"
    }
};
