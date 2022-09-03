package com.tarlad.eventsmap.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.*

actual object ClientSpecific {
    actual val ktorEngine: HttpClientEngineFactory<*> = CIO
}