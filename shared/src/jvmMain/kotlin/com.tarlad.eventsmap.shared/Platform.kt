package com.tarlad.eventsmap.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual object Platform {
    actual val platform: String = "JVM"
    actual val ktorEngine: HttpClientEngineFactory<*> = OkHttp
}