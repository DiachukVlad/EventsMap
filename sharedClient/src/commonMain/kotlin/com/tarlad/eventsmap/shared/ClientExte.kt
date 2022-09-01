package com.tarlad.eventsmap.shared

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


val client = HttpClient(Platform.ktorEngine) {
    install(ContentNegotiation) {
        json(Json{
            isLenient = true
            prettyPrint = true
        })
    }
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }
}

suspend fun HttpClient.test(): String {
    return get("http://192.168.0.19:8080/").bodyAsText()
}