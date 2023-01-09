package com.tarlad.eventsmap.shared.auth

enum class AuthView {
    SignUp, SignIn
}

data class AuthState(
    val currentView: AuthView = AuthView.SignIn
)
