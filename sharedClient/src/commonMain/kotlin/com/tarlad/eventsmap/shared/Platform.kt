package com.tarlad.eventsmap.shared

import io.ktor.client.engine.*

expect object Platform {
    val platform: String
    val ktorEngine: HttpClientEngineFactory<*>
}