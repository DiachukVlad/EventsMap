package com.tarlad.eventsmap.shared.auth

sealed class AuthEvent {
    data class SignInClick(val email: String, val pass: String) : AuthEvent()
    data class SignUpClick(val email: String, val pass: String) : AuthEvent()
    object HaveAccountClick : AuthEvent()
    object DoNotHaveAccountClick : AuthEvent()
}