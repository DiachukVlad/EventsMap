package com.tarlad.eventsmap.models

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val name: String,
    val lat: Long,
    val lon: Long,
    val hashTags: List<String>
) {
    var id = 0
}
