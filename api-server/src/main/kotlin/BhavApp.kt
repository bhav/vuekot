import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import main.kotlin.vuekot.routes.event.EventRoutes


fun Application.module() {

    EventRoutes(this)

    install(CORS) {
        //docker would be localhost
        //host("vuekot-frontend-app:80")
        anyHost()
        header(HttpHeaders.Authorization)

    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}
