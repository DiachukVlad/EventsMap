package com.tarlad.eventsmap.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*

actual object Platform {
    actual val platform: String = "IOS"
    actual val ktorEngine: HttpClientEngineFactory<*> = CIO
}