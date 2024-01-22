<script>
import { store } from './../store.js';
import axios from 'axios'

export default {
    name: "AppBook",
    data() {
        return {
            store,
            books: [],
            convertedData: "",
            userName: "",
            userSurname: "",
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
        },
        deleteBook(id) {
            if (confirm("Sei sicuro di voler eliminare il libro?")) {
                let currentDateTime = new Date().toLocaleString();
                this.convertedData = this.convertDataFormat(currentDateTime);

                let bookDeleteUrl = store.pathApiBookDetail;

                let dataCodificata = encodeURIComponent(this.convertedData);

                axios.post(bookDeleteUrl + id + '/delete', dataCodificata)
                    .then(res => {
                        console.log(res.data);
                        this.getBooksList();
                    })
                    .catch(err => {
                        console.log(err);
                    });
            }
        },
        convertDataFormat(currentDateTime) {
            // Divido la data e l'ora utilizzando lo spazio come separatore
            let [datePart, timePart] = currentDateTime.split(", ");
            // Divido la parte della data in giorno, mese e anno
            let [day, month, year] = datePart.split("/");
            // Aggiungo lo zero iniziale se necessario per il month e il day
            if (month.length === 1) {
                month = "0" + month;
            }
            if (day.length === 1) {
                day = "0" + day;
            }
            // Ricostruisco la data nel formato YYYY-MM-DD
            let DataConverted = `${year}-${month}-${day} ${timePart}`;
            return DataConverted;
        }
    },
    mounted() {
        this.getBooksList();
    },
    created() {
        let userNameArray = this.store.arrayUsers.find(user => user.id === this.store.userIdLogin);
        if (userNameArray) {
            this.userName = userNameArray.firstName;
            this.userSurname = userNameArray.lastName;
        }
    }
}
</script>

<template>
    <div class="container_list d-flex justify-content-center align-items-center">
        <div class="sub_container d-flex justify-content-center text-center" style="height: 90%; overflow-y: auto;">
            <div style="width: 85%;">
                <h1>LA MIA LIBRERIA</h1>
                <router-link :to="{ name: 'home' }">
                    <button class="btn btn-primary" style="margin-bottom: 20px;">LOGOUT</button>
                </router-link>
                <div v-if="store.userIdLogin == 0" style="font-weight: bold; font-size: 40px;">NON SEI AUTORIZZATO AD
                    ACCEDERE.</div>
                <div v-else>
                    <h3>Ciao <span style=" font-weight: bold;">{{ this.userName }} {{
                        this.userSurname }}</span>, ecco la tua lista di libri:</h3>
                    <div class="d-flex">
                        <router-link :to="{ name: 'book-create', params: { id: this.$route.params.id } }">
                            <button class="btn btn-dark" style="margin-bottom: 20px; text-align: left;">Aggiungi
                                libro</button>
                        </router-link>
                    </div>
                    <div v-if="this.books.length == 0" style="font-size: 40px; font-weight: bold;">NESSUN LIBRO PRESENTE.
                    </div>
                    <div v-else>
                        <table class="table" style="text-align: left; border: 1px;">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Titolo</th>
                                    <th scope="col">Autore</th>
                                    <th scope="col">ISBN</th>
                                    <th scope="col">Letture</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(book, index) in this.books" :key="index">
                                    <th scope="row">{{ index + 1 }}</th>
                                    <td>{{ book.title }}</td>
                                    <td>{{ book.author }}</td>
                                    <td>{{ book.isbn }}</td>
                                    <td>{{ book.numberOfCompleteReadings }}</td>
                                    <td>
                                        <router-link :to="{ name: 'book-detail', params: { id: book.id } }">
                                            <i class="fa-solid fa-magnifying-glass btn btn-dark"
                                                style="height: 30px; border-radius: 20px;"></i>
                                        </router-link>
                                    </td>
                                    <td>
                                        <router-link :to="{ name: 'book-edit', params: { id: book.id } }">
                                            <i class="fa-solid fa-pen-to-square btn btn-dark"
                                                style="height: 30px; border-radius: 20px;"></i>
                                        </router-link>
                                    </td>
                                    <td>
                                        <form @submit.prevent="deleteBook(book.id)">
                                            <button type="submit" style="border: none; background-color: white !important;">
                                                <i class="fa-solid fa-trash btn btn-dark"
                                                    style="height: 30px; border-radius: 20px;"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
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
        margin: 2rem 0 2rem 0;
    }

    h3 {
        margin-bottom: 2rem;
    }
}
</style>