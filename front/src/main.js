import Vue from 'vue'
import App from './App.vue'
import About from './components/About.vue'
import CreateEventComponent from './components/CreateEventComponent.vue'
import ViewEventsComponent from './components/ViewEventsComponent.vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)
//define your routes
const routes = [{path: '/create', component: CreateEventComponent}, 
                {path: '/about', component: About},
                {path: '/view', component: ViewEventsComponent}]

const router = new VueRouter({
  routes, // short for routes: routes
  mode: 'history'
})


new Vue({
  el: 'app',
  components:{App, CreateEventComponent, About, ViewEventsComponent},
  router
}).$mount('app')
