<template>
  <!-- @submit handles any form of submission. -->
  <!-- .prevent keeps the event from bubbling around and doing anything else. -->
  <div>
    <h3>{{message}}</h3>
    
     <ul v-if="errors && errors.length">
        <li v-for="error of errors">
            <p><strong>{{error.response.statusText}}</strong></p>
        </li>
      </ul>

  <form @submit.prevent="handleSubmit">
    <label>
      Event Name:
      <input type="text" v-model="event.name"/>
    </label>
    <label>
      Event Email Contact:
      <input type="email" v-model="event.email"/>
    </label>
    <label>
      Event Type:
      <select v-model="event.eventType">
        <option disabled value="">Please select one</option>
        <option>A</option>
        <option>B</option>
        <option>C</option>
      </select>
    </label>
    <br>
    <datetime format="DD/MM/YYYY h:i:s" width="300px" v-model="event.eventDate"></datetime>   
    <button type="submit">Submit</button>

  </form>
  <br>
  
  
  <h3> Setting up Event with Name: {{event.name}} </h3>

  
  </div>
   
</template>

<script>
import datetime from 'vuejs-datetimepicker';
import axios from 'axios';

export default {
  data() {
    return {
      event: {
        name: '',
        email: '',
        eventType: '',
        eventDate: ''
      },
      message: '',
      errors: []
    }
  },
  
  components: { datetime },


  methods: {
    handleSubmit(event) {
        axios.post(`http://localhost:8080/api/event`, {event_name: this.event.name, event_contact_email: this.event.email, event_type: this.event.eventType})
        .then(response => {
          if (response.status == 201) {
            this.message = 'Event ' + this.event.name + ' successfully created' 
          } else {
            this.message = 'Sorry Could not create event. Something went wrong'  
          }
          
        })
        .catch(e => {
           this.errors.push(e);
           this.message = 'Sorry Could not create event'
        
        })
      
        
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
