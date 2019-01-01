package main.kotlin.vuekot.data

import main.kotlin.vuekot.model.event.Event
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


interface VueKotDao {

    suspend fun createEvent(event: Event)

    suspend fun getAllEvents(): List<Event>
}

object ExposedVueKotDao: VueKotDao {

    private val db = Database.connect("jdbc:postgresql://postgres-db:5432/vuekot_events", driver = "org.postgresql.Driver",
                        user = "postgres", password = "postgres")

    override suspend fun createEvent(event: Event) {
        transaction {
            Events.insert {
                it[eventName] = event.name
                it[eventType] = event.type
                it[eventContactEmail] = event.emailAddress

            }
        }
    }

    override suspend fun getAllEvents(): List<Event> {
        val events = transaction {
            Events.selectAll().map {
                Event(it[Events.eventName], it[Events.eventContactEmail], it[Events.eventType])
            }

        }
        return events;
    }

}

object Events: Table() {
    val eventName = varchar("event_name", 255)
    val eventType = varchar("event_type", length = 255)
    val eventContactEmail = varchar("event_contact_email", length = 255)

}