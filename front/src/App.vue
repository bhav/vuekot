<template>
  <div id="app">
    <img src="./assets/logo.png">
    <navbar-component></navbar-component>
    <div class="container">
      <router-view></router-view>
    </div>
</div>

</template>

<script>
import NavbarComponent from './components/NavbarComponent.vue'

export default {
  name: 'app',
  components: {NavbarComponent},
  data () {
    return {
      msg: 'Welcome to Your Vue.js App Bhavlo',
      checkedNames: []
    }
  }
}
</script>

<style scoped>
/* #app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
} */
</style>
