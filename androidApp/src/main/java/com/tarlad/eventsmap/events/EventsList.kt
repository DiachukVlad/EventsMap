package com.tarlad.eventsmap.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tarlad.eventsmap.shared.models.Event

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventsList() {
    val vm = LocalEventsViewModel.current
    val events by vm.events.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(events, key = { it.id }) {
            EventItem(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .animateItemPlacement(),
                it
            )
        }
    }
}

@Composable
fun EventItem(modifier: Modifier = Modifier, event: Event) {
    Surface(elevation = 6.dp) {
        Text(event.toString())
    }
}