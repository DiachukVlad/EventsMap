package evenst

import base.badRequestOnError
import base.conflictOnError
import com.mongodb.reactivestreams.client.MongoCollection
import org.litote.kmongo.coroutine.*
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import org.koin.ktor.ext.inject

fun Routing.eventsRouting() {
    val collection by inject<CoroutineCollection<Event>>()

    post("/events") {
        val event: Event = badRequestOnError { call.receive() } ?: return@post
        val insertedId = conflictOnError { collection.insertOne(event).insertedId?.asString()?.value } ?: return@post
        call.respond(event.apply { id = insertedId })
    }

    get("/events") {
        val events = collection.find().toList()
        call.respond(events)
    }
}

