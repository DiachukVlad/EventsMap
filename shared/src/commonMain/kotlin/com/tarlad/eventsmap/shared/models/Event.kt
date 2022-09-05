package com.tarlad.eventsmap.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val name: String,
    val lat: Long = 123,
    val lon: Long = 0,
    val hashTags: List<String> = listOf(),
    val date: Long = 0,
    val text: String = "",
    val files:List<String> = listOf(),
    val price: String = "",
    val isApproveAutomatically: Boolean = true,
    val visibility: Visibility = Visibility.Public,
    val isCommentsEnabled: Boolean = true,
    val maxAmountOfPeople: Int = Int.MAX_VALUE
): BaseModel() {

    enum class Visibility {
        Draft, Private, Public
    }
}
