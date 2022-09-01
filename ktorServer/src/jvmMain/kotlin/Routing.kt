import com.tarlad.eventsmap.models.Event
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val events = MutableStateFlow(listOf<Event>())
val lock = Mutex()
var maxId = 0

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText { "Hello world" }
        }

        post("/addEvent") {
            val event: Event = call.receive()
            lock.withLock {
                events.tryEmit(events.value + event.apply { id = maxId++ })
            }
        }

        webSocket("/events") {
            events.collect {
                sendSerialized(it)
            }
        }
    }
}