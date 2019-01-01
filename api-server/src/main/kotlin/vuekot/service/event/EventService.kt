package main.kotlin.vuekot.service.event

import main.kotlin.vuekot.data.DbContext
import main.kotlin.vuekot.data.ExposedVueKotDao
import main.kotlin.vuekot.model.event.Event


interface EventService {

    suspend fun createEvent(eventName: String, eventType: String, emailAddress: String) : EventResult

    suspend fun getAllEvents() : List<Event>

    suspend fun getEventByName(eventName: String) : Event?
}

class VueKotEventService(dbContext: DbContext) : EventService {

    val dbContext = dbContext

    override suspend fun createEvent(eventName: String, eventType: String, emailAddress: String): EventResult {
        val event = Event(eventName, emailAddress, eventType)
        if (ExposedVueKotDao.getInstance(dbContext).getAllEvents().map { event -> event.name }.contains(eventName)) {
            return EventResult.FailResult
        }
        //events.add(event)
        ExposedVueKotDao.getInstance(dbContext).createEvent(event)
        return EventResult.SuccessfulResult(event)
    }

    override suspend fun getAllEvents(): List<Event> {
        return ExposedVueKotDao.getInstance(dbContext).getAllEvents()
    }

    override suspend fun getEventByName(eventName: String): Event? {
        return ExposedVueKotDao.getInstance(dbContext).getAllEvents().filter { e -> e.name.equals(eventName) }.firstOrNull()
    }
}

sealed class EventResult {
    data class SuccessfulResult(val event: Event) : EventResult()
    object FailResult : EventResult()

}

