import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import main.kotlin.vuekot.routes.event.EventRoutes

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080){
        EventRoutes(this)

        install(CORS) {
            host("localhost:80")
            header(HttpHeaders.Authorization)
        }

    }.start(wait = true)
}
