package com.tarlad.eventsmap.shared

import io.ktor.client.engine.*

object ClientConstants {
    const val SERVER_HOST = "192.168.0.33"
    const val SERVER_PORT = 8080
    const val SERVER_URL = "http://$SERVER_HOST:$SERVER_PORT"
}

expect object ClientSpecific {
    val ktorEngine: HttpClientEngineFactory<*>
}