package com.tarlad.eventsmap.shared.events

import com.tarlad.eventsmap.models.Event
import com.tarlad.eventsmap.shared.base.BaseViewModel
import com.tarlad.eventsmap.shared.base.asCommonFlow
import io.ktor.client.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class EventsViewModel(private val client: HttpClient) : BaseViewModel() {
    val events = MutableStateFlow(listOf<Event>())
    val name = MutableStateFlow("default name")
    val lat = MutableStateFlow(0L)
    val lon = MutableStateFlow(0L)

    val nameC = name.asCommonFlow()

    override fun onStart() {
        viewModelScope.launch {
            client.allEvents().collect {
                name.emit(it.size.toString())
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