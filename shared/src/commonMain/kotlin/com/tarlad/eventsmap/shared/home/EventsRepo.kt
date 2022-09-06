package com.tarlad.eventsmap.shared.home

import com.tarlad.eventsmap.shared.ClientConstants
import com.tarlad.eventsmap.shared.CustomDispatchers
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.withContext

suspend fun HttpClient.allEvents(): List<Event> =
    get(ClientConstants.SERVER_URL + "/events").body()


suspend fun HttpClient.sendEvent(event: Event) = withContext(CustomDispatchers.io) {
    post(ClientConstants.SERVER_URL + "/events") {
        contentType(ContentType.Application.Json)
        setBody(event)
    }
}