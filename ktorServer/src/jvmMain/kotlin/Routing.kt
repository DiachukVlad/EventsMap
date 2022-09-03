import com.tarlad.eventsmap.shared.models.Event
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val events = MutableStateFlow(
    listOf(
        Event("First", 0, 0, listOf())
            .apply { id = 0 })
)
val lock = Mutex()
var maxId = events.value.size

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText { "Hello world" }
        }

        post("/addEvent") {
            val event: Event = call.receive()
            lock.withLock {
                events.emit(events.value + event.apply { id = maxId++ })
            }
        }

        webSocket("/events") {
            events.collect {
                sendSerialized(it)
            }
        }
    }
}