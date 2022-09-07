package com.tarlad.eventsmap.shared.home

import com.tarlad.eventsmap.shared.base.BaseViewModel
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val client: HttpClient): BaseViewModel() {
    val events = MutableStateFlow(listOf<Event>())
    val error = MutableStateFlow<String?>(null)
    val tappedLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val showAddEventSheet = MutableStateFlow(false)

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

    fun onLongClick(lat: Double, lon: Double) = tappedLocation.tryEmit(lat to lon)
}