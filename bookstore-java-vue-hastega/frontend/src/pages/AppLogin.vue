<script>
import { store } from './../store.js';

export default {
  name: "AppLogin",
  data() {
    return {
      store,
    }
  },
  methods: {

  }
}
</script>

<template>
  <div class="container_login d-flex justify-content-center align-items-center">
    <div class="sub_container d-flex justify-content-center text-center" style="height: 90vh;">
      <div>
        <h1>Benvenuto</h1>
        <h3>Seleziona l'account con cui desideri accedere:</h3>
        <select name="user" id="user" v-model="store.userIdLogin">
          <option value="0"></option>
          <option v-for="(user, index) in store.arrayUsers" :key="index" :value="user.id">
            {{ user.firstName }} {{ user.lastName }}
          </option>
        </select>
        <router-link :to="{ name: 'book-list', params: { id: store.userIdLogin } }">
          <button type="button" class="btn btn-primary button_login"
            :disabled="this.store.userIdLogin == 0">ACCEDI</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '../styles/partials/variables.scss' as *;
@use '../styles/partials/mixins.scss' as *;

.container_login {
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

  select {
    width: 100%;
    padding: 10px;
    font-size: 1.2rem;
  }

  .button_login {
    margin-top: 140px;
    width: 200px;
    padding: 20px;
    font-size: 1.2rem;
  }
}
</style>