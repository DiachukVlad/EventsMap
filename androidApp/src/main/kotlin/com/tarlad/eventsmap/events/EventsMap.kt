package com.tarlad.eventsmap.events

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diachuk.routing.Route

object EventsMapRoute : Route() {
    @Composable
    override fun Content() {
        EventsMapScreen()
    }
}

@Composable
fun EventsMapScreen() {
    Box(Modifier.fillMaxSize()) {

    }
}