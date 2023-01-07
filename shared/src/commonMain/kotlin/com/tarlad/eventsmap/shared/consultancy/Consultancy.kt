package com.tarlad.eventsmap.shared.consultancy

import com.tarlad.eventsmap.shared.models.BaseModel

@kotlinx.serialization.Serializable
data class Consultancy(
val name: String
): BaseModel()