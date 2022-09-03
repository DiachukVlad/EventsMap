package com.tarlad.eventsmap.shared.events

import com.tarlad.eventsmap.shared.ClientConstants
import com.tarlad.eventsmap.shared.ClientConstants.SERVER_HOST
import com.tarlad.eventsmap.shared.ClientConstants.SERVER_PORT
import com.tarlad.eventsmap.shared.CustomDispatchers
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

suspend fun HttpClient.allEvents(): Flow<List<Event>> = withContext(CustomDispatchers.io) {
    flow {
        webSocket(
            method = HttpMethod.Get,
            host = SERVER_HOST,
            port = SERVER_PORT,
            path = "/events"
        ) {
            while (true) {
                emit(receiveDeserialized())
            }
        }
        close()
    }
}

suspend fun HttpClient.sendEvent(event: Event) = withContext(CustomDispatchers.io) {
    post(ClientConstants.SERVER_URL + "/addEvent") {
        contentType(ContentType.Application.Json)
        setBody(event)
    }
}