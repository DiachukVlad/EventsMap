package com.tarlad.eventsmap.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.shared.auth.AuthEvent
import com.tarlad.eventsmap.shared.auth.AuthView
import com.tarlad.eventsmap.shared.auth.AuthViewModel

val AuthScreen = createRoute<AuthViewModel> { vm ->
    val state by vm.state.collectAsState()
    Box(Modifier.fillMaxSize()) {
        if (state.currentView == AuthView.SignUp) {
            SignUp(vm::onReceiveEvent)
        } else {
            SignIn(vm::onReceiveEvent)
        }
    }
}

@Composable
private fun SignUp(onEvent: (AuthEvent) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        var email by remember { mutableStateOf("qwe") }
        var pass by remember { mutableStateOf("asdas") }

        OutlinedTextField(value = email, onValueChange = { email = it })
        OutlinedTextField(value = pass, onValueChange = { pass = it })

        OutlinedButton(onClick = { onEvent(AuthEvent.SignUpClick("qwe", "asdas")) }) {
            Text("SignUn")
        }
        OutlinedButton(onClick = { onEvent(AuthEvent.HaveAccountClick) }) {
            Text("Do have an account")
        }
    }
}

@Composable
private fun SignIn(eventListener: (AuthEvent) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        var email by rememberSaveable { mutableStateOf("qwe") }
        var pass by rememberSaveable { mutableStateOf("asdas") }

        OutlinedTextField(value = email, onValueChange = { email = it })
        OutlinedTextField(value = pass, onValueChange = { pass = it })

        OutlinedButton(onClick = { eventListener(AuthEvent.SignInClick("qwe", "asdas")) }) {
            Text("SignIn")
        }
        OutlinedButton(onClick = { eventListener(AuthEvent.DoNotHaveAccountClick) }) {
            Text("Do not have account")
        }
    }
}
