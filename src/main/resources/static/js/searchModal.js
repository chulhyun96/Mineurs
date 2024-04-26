const { createApp } = Vue;

createApp({

    data() {
        return {

            isSearchModalActive: false
        };
    },
    methods: {
        searchModalClickHandler()  {
            this.isSearchModalActive = !this.isSearchModalActive
        }
    },

}).mount("header");
