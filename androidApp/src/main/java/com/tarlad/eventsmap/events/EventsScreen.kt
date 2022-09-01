package com.tarlad.eventsmap.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import com.tarlad.eventsmap.base.LocalViewModelProvider
import com.tarlad.eventsmap.shared.client
import com.tarlad.eventsmap.shared.events.EventsViewModel

val LocalEventsViewModel = compositionLocalOf { EventsViewModel(client) }

@Composable
fun EventsScreen() {
    val vm = EventsViewModel(client)
    LocalViewModelProvider(vm, LocalEventsViewModel) {
        val name by vm.name.collectAsState()
        val lat by vm.lat.collectAsState()
        val lon by vm.lon.collectAsState()
        Column {
            TextField(name, { vm.name.tryEmit(it) }, label = { Text("Name") })
            TextField(
                lat.toString(),
                { it.toLongOrNull()?.let { it1 -> vm.lat.tryEmit(it1) } },
                label = { Text("Lat") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            TextField(
                lon.toString(),
                { it.toLongOrNull()?.let { it1 -> vm.lon.tryEmit(it1)} },
                label = { Text("Lon") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Button(onClick = vm::onCreateEventClick) {
                Text("Create")
            }
            EventsList()
        }
    }
}