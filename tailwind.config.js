/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/main/resources/templates/**/*.html"],
    theme: {
        extend: {},

    },
    plugins: [
        require("daisyui"),
        require('@tailwindcss/typography')],
    daisyui: {
        themes: ["light", "dark", "cupcake"],
    },
}

