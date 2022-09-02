package com.tarlad.eventsmap.shared

import com.tarlad.eventsmap.models.Event
import com.tarlad.eventsmap.shared.events.EventsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun EventsViewModel.observeEvents(callback: (List<Event>)->Unit) {
    viewModelScope.launch {
        withContext(Dispatchers.Default) {
            events.collect{
                callback(it)
            }
        }
    }
}