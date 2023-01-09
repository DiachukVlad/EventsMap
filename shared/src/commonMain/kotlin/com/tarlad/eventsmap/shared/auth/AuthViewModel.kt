package com.tarlad.eventsmap.shared.auth

import com.tarlad.eventsmap.shared.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(val repo: AuthRepo) : BaseViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onReceiveEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SignUpClick -> onSignUpEvent(event)
            AuthEvent.DoNotHaveAccountClick -> _state.update { copy(currentView = AuthView.SignUp) }
            AuthEvent.HaveAccountClick -> _state.update { copy(currentView = AuthView.SignIn) }
            is AuthEvent.SignInClick -> onSignInEvent(event)
        }
    }

    private fun onSignUpEvent(event: AuthEvent.SignUpClick) {
        viewModelScope.launch {
            repo.signUp(
                User(
                    email = event.email,
                    pass = event.pass
                )
            ).onSuccess { user ->
                println("user = ${user}")
            }
        }
    }

    private fun onSignInEvent(event: AuthEvent.SignInClick) {
        viewModelScope.launch {
            repo.signIn(
                User(
                    email = event.email,
                    pass = event.pass
                )
            ).onSuccess { user ->
                println("user = ${user}")
            }
        }
    }
}