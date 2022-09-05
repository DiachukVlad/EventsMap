package com.tarlad.eventsmap.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
  val email: String,
  val password: String,
  val phone: String,
  val name: String,
  val funnyName: String
)
