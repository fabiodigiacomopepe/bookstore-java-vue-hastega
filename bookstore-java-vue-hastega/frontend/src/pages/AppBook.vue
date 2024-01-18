<script>
import { store } from './../store.js';
import axios from 'axios'

export default {
    name: "AppLogin",
    data() {
        return {
            store,
        }
    },
    methods: {

    },
    mounted() {
        const userId = this.$route.params.id;
        console.log(userId);
    },
}
</script>

<template>
    <div class="container_list d-flex justify-content-center align-items-center">
        <div class="sub_container d-flex justify-content-center text-center" style="height: 90vh;">
            <div>
                <h1>Lista libri</h1>
                <h3></h3>
            </div>
        </div>
    </div>
</template>

<style scoped lang="scss">
@use '../styles/partials/variables.scss' as *;
@use '../styles/partials/mixins.scss' as *;

.container_list {
    width: 100%;
    height: 100vh;

    .sub_container {
        width: 80%;
        margin: 0 auto;
        height: 30%;
        background-color: white;
        border-radius: 20px;
    }

    h1 {
        margin: 2rem 0;
    }
}
</style>