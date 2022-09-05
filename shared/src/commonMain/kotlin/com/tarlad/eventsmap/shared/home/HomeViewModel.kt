package com.tarlad.eventsmap.shared.home

import com.tarlad.eventsmap.shared.base.BaseViewModel
import com.tarlad.eventsmap.shared.events.allEvents
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val client: HttpClient): BaseViewModel() {
    val events = MutableStateFlow(listOf<Event>())
    val error = MutableStateFlow(null as String?)

    override fun onStart() {
        viewModelScope.launch {
            try {
                events.emit(client.allEvents())
            } catch (e: Exception) {
                e.printStackTrace()
                error.emit(e.message)
            }
        }
    }
}