package com.tarlad.eventsmap.shared.events

import com.tarlad.eventsmap.shared.base.BaseViewModel
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.client.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class EventsViewModel(private val client: HttpClient) : BaseViewModel() {
    val events = MutableStateFlow(listOf<Event>())
    val error = MutableStateFlow(null as String?)
    val name = MutableStateFlow("default name")
    val lat = MutableStateFlow(0L)
    val lon = MutableStateFlow(0L)


    override fun onStart() {
        viewModelScope.launch {
            try {
                client.allEvents()
                    .collect {
                        name.emit(it.size.toString())
                        events.emit(it)
                    }
            } catch (e: Exception) {
                println(e)
                error.emit(e.message)
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