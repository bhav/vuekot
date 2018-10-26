package main.kotlin.vuekot.routes.event

import com.github.salomonbrys.kotson.jsonObject
import com.github.salomonbrys.kotson.nullString
import com.google.gson.JsonObject
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import main.kotlin.vuekot.model.event.Event
import main.kotlin.vuekot.routes.BaseRoute
import main.kotlin.vuekot.service.event.EventService
import main.kotlin.vuekot.service.event.VueKotEventService

/**
 * curl -i -X POST -H "Content-Type:application/json" -d'{"event_name":"TestEvent", "event_contact_email":"tester@events.com", "event_type":"C"}' 'http://localhost:8080/api/event'
 */
class EventRoutes constructor(application: Application) : BaseRoute() {

    val eventService:EventService = VueKotEventService()

    init {
        application.routing {
            route("/api") {

                route("/event") {

                    route("/{eventName}") {

                        get {
                            val eventName = call.parameters.get("eventName")
                            eventName?.let {
                                val foundEvent = eventService.getEventByName(eventName)
                                call.respondWithJson(foundEvent) {eventToJsonObject(it)}
                            } ?: call.respond(HttpStatusCode.BadRequest)

                        }

                    }

                    get {
                        val events = eventService.getAllEvents()
                        call.respondWithJsonResults(events, HttpStatusCode.OK){eventToJsonObject(it)}
                    }

                    post {
                        val json = call.receiveJson()
                        val eventName = json["event_name"].nullString
                        val eventContactEmail = json["event_contact_email"].nullString
                        val eventType = json["event_type"].nullString

                        if (allDefined(eventName, eventContactEmail, eventType)) {
                            val createdEvent = eventService.createEvent(eventName!!, eventType!!, eventContactEmail!!)
                            call.respondWithJsonId(createdEvent.name, HttpStatusCode.Created)
                        } else {
                            call.respond(HttpStatusCode.BadRequest)
                        }

                    }

                }
            }


        }
    }

    private fun eventToJsonObject(event: Event): JsonObject {
        return  jsonObject(
                "event_name" to event.name,
                "event_contact_email" to event.emailAddress,
                "event_type" to event.type
        )
    }

}