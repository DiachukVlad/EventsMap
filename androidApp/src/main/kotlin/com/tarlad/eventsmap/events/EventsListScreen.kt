package com.tarlad.eventsmap.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.shared.events.EventsViewModel
import com.tarlad.eventsmap.shared.models.Event
import org.koin.androidx.compose.get

val EventsListScreen = createRoute<EventsViewModel> {
    Box(Modifier.fillMaxSize()) {
        EventsList()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventsList(vm: EventsViewModel = get()) {
    val events by vm.events.collectAsState()

    Column(Modifier.fillMaxSize()) {

        CircularProgressIndicator()
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(events, key = { it.id?:"def" }) {
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

}

@Composable
fun EventItem(modifier: Modifier = Modifier, event: Event) {
    Surface(elevation = 6.dp) {
        Text(event.toString())
    }
}