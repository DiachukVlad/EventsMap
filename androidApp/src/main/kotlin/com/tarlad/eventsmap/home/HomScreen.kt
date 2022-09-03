package com.tarlad.eventsmap.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.diachuk.routing.LocalRouting
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.events.EventsListScreen

val HomScreen = createRoute {
    Column(Modifier.fillMaxWidth()) {
        val routing = LocalRouting
        Text("This is home screen")

        Button({ routing.push(EventsListScreen) }) {
            Text("Go to list")
        }
        Button({}) {
            Text("Show on map")
        }
    }
}
