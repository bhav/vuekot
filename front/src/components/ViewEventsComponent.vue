<template>
  
    
    <div>
        <page-heading-component></page-heading-component>
        <event-list-component></event-list-component>
    </div>

</template>

<script>
import PageHeadingComponent from './PageHeadingComponent.vue'
import EventListComponent from './EventListComponent.vue'

export default {
  name: 'app',
  components: {PageHeadingComponent, EventListComponent},
  data () {
    return {}
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
