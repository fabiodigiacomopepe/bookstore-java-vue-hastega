<script>
import { store } from './../store.js';
import axios from 'axios'

export default {
    name: "AppBook",
    data() {
        return {
            store,
            books: [],
        }
    },
    methods: {
        getBooksList() {
            let bookListUrl = store.pathApiBooks;

            axios
                .get(bookListUrl + store.userIdLogin)
                .then(
                    res => {
                        this.books = res.data;
                    })
                .catch(err => {
                    console.log(err);
                })
        }
    },
    mounted() {
        this.userId = this.$route.params.id;
        this.getBooksList();
    }
}
</script>

<template>
    <div class="container_list d-flex justify-content-center align-items-center">
        <div class="sub_container d-flex justify-content-center text-center" style="height: 90%; overflow-y: auto;">
            <div>
                <h1>LA MIA LIBRERIA</h1>
                <router-link :to="{ name: 'home' }">
                    <button class="btn btn-primary" style="margin-bottom: 20px;">LOGOUT</button>
                </router-link>
                <h3>Ciao <span style=" font-weight: bold;">{{ books.firstName }} {{ books.lastName }}</span>, ecco la
                    tua
                    lista di libri:</h3>
                <table class="table" style="text-align: left; border: 1px;">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Titolo</th>
                            <th scope="col">Autore</th>
                            <th scope="col">ISBN</th>
                            <th scope="col">Letture</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(book, index) in this.books.books" :key="index">
                            <th scope="row">{{ index + 1 }}</th>
                            <td>{{ book.title }}</td>
                            <td>{{ book.author }}</td>
                            <td>{{ book.isbn }}</td>
                            <td>{{ book.numberOfCompleteReadings }}</td>
                            <router-link :to="{ name: 'book-detail', params: { id: book.id } }">
                                <i class="fa-solid fa-magnifying-glass btn btn-dark"
                                    style="position: relative; top: 5px; height: 30px; border-radius: 20px;"></i>
                            </router-link>
                        </tr>
                    </tbody>
                </table>
                <br>
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
        margin: 2rem 0 1rem 0;
    }

    h3 {
        margin-bottom: 2rem;
    }
}
</style>