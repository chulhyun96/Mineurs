/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/main/resources/templates/**/*.html"],
    theme: {
        extend: {},
        container: {
            center: true,
            padding: '1rem',
            screens: {
                sm: '100%',
                md: '100%',
                lg: '1600px',
                xl: '2220px',
            },
        },
    },
    plugins: [
        require("daisyui"),
        require('@tailwindcss/typography')],
    daisyui: {
        themes: ["light", "dark", "cupcake"],
    },
}

