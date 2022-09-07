package com.tarlad.eventsmap.shared.addEvent

import com.tarlad.eventsmap.shared.base.BaseViewModel
import com.tarlad.eventsmap.shared.home.addEvent
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddEventViewModel(private val client: HttpClient): BaseViewModel() {
    val selectedLocation = MutableStateFlow(0.0 to 0.0)
    val name = MutableStateFlow("")

    fun onLongClick(lat: Double, lon: Double) = selectedLocation.tryEmit(lat to lon)
    fun onNameChange(value: String) = name.tryEmit(value)

    fun addEvent(onDone: ()->Unit) {
        val event = Event(
            name = name.value,
            lat = selectedLocation.value.first,
            lon = selectedLocation.value.second,
        )
//        println(client)
        viewModelScope.launch {
            client.addEvent(event)
            onDone()
        }
    }
}