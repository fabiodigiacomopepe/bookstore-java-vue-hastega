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
            page: '',
            convertedData: '',
            errors: {
                title: '',
                author: '',
                numberOfCompleteReadings: '',
                isbn: '',
                plot: '',
                isValid: '',
            }
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
            this.validate();
            console.log(this.isValid);
            if (this.isValid) {
                if (this.page == "book-edit") {
                    let bookDetailUrl = store.pathApiBookDetail;

                    axios.post(bookDetailUrl + this.book_id + '/edit', this.book)
                        .then(res => {
                            console.log(res.data);
                            this.$router.push({ name: 'book-detail', params: { id: this.book_id } });
                        })
                        .catch(err => {
                            console.log(err);
                        });
                } else if (this.page == "book-create") {
                    let bookCreateUrl = store.pathApiBooks;

                    let currentDateTime = new Date().toLocaleString();
                    this.convertedData = this.convertDataFormat(currentDateTime);
                    let dataCodificata = encodeURIComponent(this.convertedData);

                    axios.post(bookCreateUrl + this.store.userIdLogin + '/create', {
                        author: this.book.author,
                        createdAt: dataCodificata,
                        deletedAt: null,
                        isbn: this.book.isbn,
                        numberOfCompleteReadings: this.book.numberOfCompleteReadings,
                        plot: this.book.plot,
                        title: this.book.title,
                    })
                        .then(res => {
                            console.log(res.data);
                            this.$router.push({ name: 'book-detail', params: { id: res.data.id } });
                        })
                        .catch(err => {
                            console.log(err);
                        });
                }
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
        },
        validate() {
            this.isValid = true;

            if (!this.book.title || this.contieneSoloSpaziVuoti(this.book.title)) {
                this.isValid = false;
                this.errors.title = "Il titolo non può essere nullo";
            } else if (this.book.title.length > 255) {
                this.isValid = false;
                this.errors.title = "Il titolo non può essere più di 255 caratteri";
            } else {
                this.errors.title = "";
            }

            if (!this.book.author || this.contieneSoloSpaziVuoti(this.book.author)) {
                this.isValid = false;
                this.errors.author = "L'autore non può essere nullo";
            } else if (this.book.author.length > 255) {
                this.isValid = false;
                this.errors.author = "L'autore non può essere più di 255 caratteri";
            } else {
                this.errors.author = "";
            }

            if (!this.book.isbn || this.contieneSoloSpaziVuoti(this.book.isbn)) {
                this.isValid = false;
                this.errors.isbn = "L'ISBN non può essere nullo";
            } else if (this.book.isbn.length > 13) {
                this.isValid = false;
                this.errors.isbn = "L'ISBN non può essere più di 13 caratteri";
            } else {
                this.errors.isbn = "";
            }

            if (this.contieneSoloSpaziVuoti(this.book.numberOfCompleteReadings)) {
                this.isValid = false;
                this.errors.numberOfCompleteReadings = "Il numero di letture complete non può essere nullo";
            } else if (this.book.numberOfCompleteReadings < 0) {
                this.isValid = false;
                this.errors.numberOfCompleteReadings = "Il numero di letture complete non può essere negativo";
            } else {
                this.errors.numberOfCompleteReadings = "";
            }

            if (!this.book.plot || this.contieneSoloSpaziVuoti(this.book.plot)) {
                this.isValid = false;
                this.errors.plot = "La trama non può essere nulla";
            } else {
                this.errors.plot = "";
            }
        },
        contieneSoloSpaziVuoti(stringa) {
            return /^ *$/.test(stringa);
        }
    },
    mounted() {
        this.book_id = this.$route.params.id;
        this.page = this.$route.name;
        if (this.page == "book-edit") {
            this.getBook();
        } else {
            this.book.numberOfCompleteReadings = 0;
        }
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
                            <span v-show="errors.title" style="color: red;">{{ errors.title }}</span>
                        </div>
                        <div>
                            <label for="author">Autore:</label><br>
                            <input type="text" name="author" v-model="this.book.author">
                            <span v-show="errors.author" style="color: red;">{{ errors.author }}</span>
                        </div>
                        <div>
                            <label for="numberOfCompleteReadings">Letture complete:</label><br>
                            <input type="number" name="numberOfCompleteReadings"
                                v-model="this.book.numberOfCompleteReadings">
                            <span v-show="errors.numberOfCompleteReadings" style="color: red;">{{
                                errors.numberOfCompleteReadings }}</span>
                        </div>
                        <div>
                            <label for="isbn">ISBN-13:</label><br>
                            <input type="text" name="isbn" v-model="this.book.isbn">
                            <span v-show="errors.isbn" style="color: red;">{{ errors.isbn }}</span>
                        </div>
                        <div>
                            <label for="plot">Trama:</label><br>
                            <textarea name="plot" cols="30" rows="10"
                                style="width: 100%; padding: 8px; border-radius: 10px;" v-model="this.book.plot"></textarea>
                            <span v-show="errors.plot" style="color: red;">{{ errors.plot }}</span>
                        </div>
                        <input type="hidden" v-model="this.book.id">
                        <button type="submit" class="btn btn-dark" style="width: 100%;">Invia</button>
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