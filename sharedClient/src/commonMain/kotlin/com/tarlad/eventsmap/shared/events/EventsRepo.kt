package com.tarlad.eventsmap.shared.events

import com.tarlad.eventsmap.shared.models.Event
import com.tarlad.eventsmap.shared.Constants
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

suspend fun HttpClient.allEvents(): Flow<List<Event>> = withContext(Dispatchers.Default) {
    flow {
        webSocket(
            method = HttpMethod.Get,
            host = Constants.SERVER_HOST,
            port = Constants.SERVER_PORT,
            path = "/events"
        ) {
            while (true) {
                emit(receiveDeserialized())
            }
        }
        close()
    }
}

suspend fun HttpClient.sendEvent(event: Event) = withContext(Dispatchers.Default){
    post(Constants.SERVER_URL+"/addEvent") {
        contentType(ContentType.Application.Json)
        setBody(event)
    }
}