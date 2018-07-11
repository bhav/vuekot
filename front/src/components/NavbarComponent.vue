<template>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
        <a class="navbar-brand" href="#">ACME INTL</a>
              
      
          <b-navbar-nav>
            <b-nav-item href="#"><router-link v-bind:to="'/create'">Create</router-link></b-nav-item>
            <b-nav-item href="#"><router-link v-bind:to="'/about'">About</router-link></b-nav-item>
            <b-nav-item href="#"><router-link v-bind:to="'/view'">View</router-link></b-nav-item>
        </b-navbar-nav>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</template>

<script>
    module.exports = {
        data(){
            return {}
        }
    }
</script>


<style scoped>

</style>