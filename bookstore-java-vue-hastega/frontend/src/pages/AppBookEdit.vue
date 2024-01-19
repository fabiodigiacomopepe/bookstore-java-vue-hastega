<script>
import { store } from './../store.js';
import axios from 'axios';

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
        getBook() {
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
        submitForm() {
            let bookDetailUrl = store.pathApiBookDetail;

            axios.post(bookDetailUrl + this.book_id + '/edit', this.book)
                .then(res => {
                    console.log(res.data);
                    this.$router.push({ name: 'book-detail', params: { id: this.book_id } });
                })
                .catch(err => {
                    console.log(err);
                });

        }
    },
    mounted() {
        this.book_id = this.$route.params.id;
        this.getBook();
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
                <div style="padding: 1rem 4rem 5rem 4rem; font-size: 20px; min-width: 100%;">
                    <form @submit.prevent="submitForm" style="text-align: left;">
                        <div>
                            <label for="title">Titolo:</label><br>
                            <input type="text" name="title" v-model="this.book.title">
                        </div>
                        <div>
                            <label for="author">Autore:</label><br>
                            <input type="text" name="author" v-model="this.book.author">
                        </div>
                        <div>
                            <label for="numberOfCompleteReadings">Letture complete:</label><br>
                            <input type="text" name="numberOfCompleteReadings" v-model="this.book.numberOfCompleteReadings">
                        </div>
                        <div>

                            <label for="isbn">ISBN-13:</label><br>
                            <input type="text" name="isbn" v-model="this.book.isbn">
                        </div>
                        <div>
                            <label for="plot">Trama:</label><br>
                            <textarea name="plot" cols="30" rows="10"
                                style="width: 100%; padding: 8px; border-radius: 10px;" v-model="this.book.plot"></textarea>
                        </div>
                        <input type="hidden" v-model="this.book.id">
                        <button type="submit">Invia</button>
                    </form>
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

        h1 {
            margin: 2rem 0;
        }

        form {
            min-width: 100%;

            div {
                margin-bottom: 20px;
            }

            input {
                width: 100%;
                padding: 8px;
                border-radius: 10px;
            }
        }
    }

}
</style>