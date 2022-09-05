package base

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend inline fun <T> PipelineContext<Unit, ApplicationCall>.respondOnError(statusCode: HttpStatusCode, block: ()->T): T? {
    try {
        return block()
    }catch (e: Exception) {
        call.respond(statusCode)
    }
    return null
}

suspend inline fun <T> PipelineContext<Unit, ApplicationCall>.badRequestOnError(block: ()->T): T? = respondOnError(
    HttpStatusCode.BadRequest, block)
suspend inline fun <T> PipelineContext<Unit, ApplicationCall>.conflictOnError(block: ()->T): T? = respondOnError(
    HttpStatusCode.Conflict, block)
