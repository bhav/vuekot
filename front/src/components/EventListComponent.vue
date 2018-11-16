<template>
    <table class="table table-bordered">
        <tbody>
            <ul v-if="events && events.length">
                <li v-for="event of events">
                    <p><strong>{{event.event_name}}</strong></p>
                    <p><strong>{{event.event_contact_email}}</strong></p>
                    <p><strong>{{event.event_type}}</strong></p>
                </li>
            </ul>

            <ul v-if="errors && errors.length">
                <li v-for="error of errors">
                    {{error.message}}
                </li>
            </ul>
        <!-- <tr v-for="staff in staffs">
            <td>{{staff.name}}</td>
            <td>{{staff.email}}</td>
            <td>{{staff.role}}</td>
        </tr> -->
        </tbody>
    </table>
</template>


<script>
import axios from 'axios';

    export default {
        name: "EventListComponent",
        
        data (){
            return {
                events: [],
                errors: []
            }
        },
          // Fetches posts when the component is created.
            mounted() {
                axios.get(`http://localhost:8080/api/event`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.events = response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
            }
    }

</script>

<style scoped>
</style>