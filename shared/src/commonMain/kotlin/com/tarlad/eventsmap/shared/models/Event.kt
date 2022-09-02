package com.tarlad.eventsmap.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val name: String,
    val lat: Long,
    val lon: Long,
    val hashTags: List<String>
): BaseModel() {
    var id = 0
}
