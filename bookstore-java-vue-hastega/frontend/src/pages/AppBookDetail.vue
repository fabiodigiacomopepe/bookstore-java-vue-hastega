<script>
import { store } from './../store.js';
import axios from 'axios'

export default {
    name: "AppBookDetail",
    data() {
        return {
            store,
            book_id: null,
            book: [],
        }
    },
    methods: {
        getBookDetail() {
            let bookDetailUrl = store.pathApiBookDetail;

            axios
                .get(bookDetailUrl + this.book_id + '/show')
                .then(
                    res => {
                        this.book = res.data;
                    })
                .catch(err => {
                    console.log(err);
                })
        },
    },
    mounted() {
        this.book_id = this.$route.params.id;
        this.getBookDetail();
    }
}
</script>

<template>
    <div class="container_list d-flex justify-content-center align-items-center">
        <div class="sub_container d-flex justify-content-center text-center" style="height: 90%; overflow-y: auto;">
            <div style="width: 100%;">
                <h1>LA MIA LIBRERIA</h1>
                <router-link :to="{ name: 'book-list', params: { id: store.userIdLogin } }">
                    <button class="btn btn-primary" style="margin-bottom: 20px;">INDIETRO</button>
                </router-link>
                <div style="text-align: left; padding: 1rem 4rem 5rem 4rem; font-size: 20px;">
                    <div><span style="font-weight: bold;">ID:</span> {{ this.book.id }}</div>
                    <div><span style="font-weight: bold;">Titolo:</span> {{ this.book.title }}</div>
                    <div><span style="font-weight: bold;">Autore:</span> {{ this.book.author }}</div>
                    <div><span style="font-weight: bold;">Letture complete:</span> {{ this.book.numberOfCompleteReadings }}
                    </div>
                    <div><span style="font-weight: bold;">ISBN-13:</span> {{ this.book.isbn }}</div>
                    <div><span style="font-weight: bold;">Creato:</span> {{ this.book.createdAt }}</div>
                    <div v-if="this.book.deletedAt != NULL"><span style="font-weight: bold;">Eliminato:</span> {{
                        this.book.deletedAt }}</div>
                    <div><span style="font-weight: bold;">Trama:</span> {{ this.book.plot }}</div>
                </div>
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