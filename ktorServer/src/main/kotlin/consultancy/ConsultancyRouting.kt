package consultancy

import com.tarlad.eventsmap.shared.consultancy.Consultancy
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineCollection

fun Routing.consultancyRouting() {
    val collection by inject<CoroutineCollection<Consultancy>>()

    get("/consultancies") {
        val events = collection.find().toList()
        collection.find()
        return@get call.respond(events)
    }
}

