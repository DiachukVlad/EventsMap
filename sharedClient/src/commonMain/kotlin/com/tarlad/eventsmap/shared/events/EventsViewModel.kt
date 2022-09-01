package com.tarlad.eventsmap.shared.events

import com.tarlad.eventsmap.models.Event
import com.tarlad.eventsmap.shared.base.BaseViewModel
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventsViewModel(private val client: HttpClient) : BaseViewModel() {
    val events = MutableStateFlow(listOf<Event>())
    val name = MutableStateFlow("")
    val lat = MutableStateFlow(0L)
    val lon = MutableStateFlow(0L)

    override fun onStart() {
        viewModelScope.launch {
            client.allEvents().collect {
                events.emit(it)
            }
        }
    }

    fun onCreateEventClick() {
        val event = Event(name.value, lat.value, lon.value, listOf())
        viewModelScope.launch {
            client.sendEvent(event)
        }
    }
}