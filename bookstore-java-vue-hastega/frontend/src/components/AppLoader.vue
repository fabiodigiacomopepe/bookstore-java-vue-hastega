<script>
export default {
    name: "AppLoader",
}
</script>

<template>
    <div id="container_loader">
        <div>   
            CARICAMENTO ...
        </div>
    </div>
</template>

<style scoped lang="scss">
@use '../styles/partials/variables.scss' as *;
@use '../styles/partials/mixins.scss' as *;

#container_loader {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 60px;
    font-weight: bold;

    div {
        background-color: white;
        padding: 3rem;
        border-radius: 20px;
    }
}
</style>
