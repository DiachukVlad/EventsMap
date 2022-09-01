package com.tarlad.eventsmap.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual object Platform {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val ktorEngine: HttpClientEngineFactory<*> = OkHttp
}