package com.tarlad.eventsmap.shared.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
open class BaseModel {
    @SerialName("_id")
    var id: String? = null
}