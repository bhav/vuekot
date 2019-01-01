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

class ExposedVueKotDao private constructor(context: DbContext): VueKotDao {

    companion object SingletonFactory {
        private var DAOINSTANCE : ExposedVueKotDao? = null
        fun getInstance(context: DbContext): ExposedVueKotDao  {

            return DAOINSTANCE ?: synchronized(this) {
                DAOINSTANCE ?: ExposedVueKotDao(context)
            }

        }
    }

    private val db = Database.connect("jdbc:postgresql://${context.host}:${context.port}/vuekot_events", driver = "org.postgresql.Driver",
                        user = context.username, password = context.password)

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

data class DbContext (val host:String, val port:String, val username:String, val password:String)