package main.kotlin.vuekot.service.event

import main.kotlin.vuekot.model.event.Event


interface EventService {

    suspend fun createEvent(eventName: String, eventType: String, emailAddress: String) : Event

    suspend fun getAllEvents() : List<Event>

    suspend fun getEventByName(eventName: String) : Event?
}

class VueKotEventService() : EventService {

    val events = arrayListOf<Event>(Event("tester", "tester@gfd.com", "A"),
                                                   Event("nester", "nester@gfd.com", "B"))

    override suspend fun createEvent(eventName: String, eventType: String, emailAddress: String): Event {
        val event = Event(eventName, emailAddress, eventType)
        events.add(event)
        return event
    }

    override suspend fun getAllEvents(): List<Event> {
        return events
    }

    override suspend fun getEventByName(eventName: String): Event? {
        return events.filter { e -> e.name.equals(eventName) }.firstOrNull()
    }
}