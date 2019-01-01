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
            //docker would be localhost
            //host("vuekot-frontend-app:80")
            anyHost()
            header(HttpHeaders.Authorization)

        }

    }.start(wait = true)
}
