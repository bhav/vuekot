package main.kotlin.vuekot.service.event

import main.kotlin.vuekot.model.event.Event


interface EventService {

    suspend fun createEvent(eventName: String, eventType: String, emailAddress: String) : EventResult

    suspend fun getAllEvents() : List<Event>

    suspend fun getEventByName(eventName: String) : Event?
}

class VueKotEventService() : EventService {
    
    val events = arrayListOf<Event>()

    override suspend fun createEvent(eventName: String, eventType: String, emailAddress: String): EventResult {
        val event = Event(eventName, emailAddress, eventType)
        if (events.map { event -> event.name }.contains(eventName)) {
            return EventResult.FailResult
        }
        events.add(event)
        return EventResult.SuccessfulResult(event)
    }

    override suspend fun getAllEvents(): List<Event> {
        return events
    }

    override suspend fun getEventByName(eventName: String): Event? {
        return events.filter { e -> e.name.equals(eventName) }.firstOrNull()
    }
}

sealed class EventResult {
    data class SuccessfulResult(val event: Event) : EventResult()
    object FailResult : EventResult()

}

