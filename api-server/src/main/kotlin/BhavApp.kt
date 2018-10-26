import io.ktor.server.engine.*
import io.ktor.server.netty.*
import main.kotlin.vuekot.routes.event.EventRoutes

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080){
        EventRoutes(this)
    }.start(wait = true)
}