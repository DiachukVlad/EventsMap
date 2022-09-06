package com.tarlad.eventsmap.shared

import com.tarlad.eventsmap.shared.models.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun EventsViewModel.observeEvents(callback: (List<Event>)->Unit) {
    viewModelScope.launch {
        withContext(Dispatchers.Main) {
            events.collect{
                callback(it)
            }
        }
    }
}