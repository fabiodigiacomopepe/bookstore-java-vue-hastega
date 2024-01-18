<script>
import { store } from './store.js'
import axios from 'axios'
import AppLoader from './components/AppLoader.vue'
import AppLogin from './pages/AppLogin.vue'


export default {
  components: {
    AppLoader,
    AppLogin,
  },
  data() {
    return {
      store
    }
  },
  methods: {
    getUsersList() {
      let userListUrl = store.pathApiUsers;

      axios
        .get(userListUrl)
        .then(
          res => {
            store.arrayUsers = res.data;
            setTimeout(() => {
              store.loading = false;
              // console.log(store.arrayUsers);
            }, 500);
          })
        .catch(err => {
          console.log(err);
        })
    },
  },
  created() {
    this.getUsersList();
  }
}
</script>

<template>
  <div class="container_fluid">
    <AppLoader v-if="store.loading" />
    <div v-else>
      <router-view></router-view>
    </div>
  </div>
</template>

<style lang="scss">
@use './styles/general.scss' as *;
@use './styles/partials/variables.scss' as *;
@use './styles/partials/mixins.scss' as *;
</style>
