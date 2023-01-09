package com.tarlad.eventsmap.shared.consultancy

import com.tarlad.eventsmap.shared.models.BaseModel

@kotlinx.serialization.Serializable
data class Consultancy(
    override val id: Int? = null,
    val name: String
) : BaseModel()