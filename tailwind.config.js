const { addDynamicIconSelectors } = require('@iconify/tailwind');

/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/main/resources/templates/**/*.html"],
    theme: {
        extend: {},
            colors:{
                'naver':'#03c75a',
                'kakao':'#FEE500'
            }

    },
    plugins: [
        require("daisyui"),
        require('@tailwindcss/typography'),
        addDynamicIconSelectors(),
    ],
    daisyui: {
        themes: ["light", "dark", "cupcake"],
    },
    
}

