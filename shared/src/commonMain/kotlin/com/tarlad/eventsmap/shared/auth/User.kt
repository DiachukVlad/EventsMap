package com.tarlad.eventsmap.shared.auth

import com.tarlad.eventsmap.shared.models.BaseModel
import kotlinx.serialization.Serializable

enum class Role {
    Student, Consultancy, Public
}

@Serializable
data class User(
    override val id: Int? = null,
    val email: String = "",
    val pass: String = "",
    var token: String = "",
    val role: Role = Role.Public
) : BaseModel()
