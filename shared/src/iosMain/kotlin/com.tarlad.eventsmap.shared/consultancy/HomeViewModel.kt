package com.tarlad.eventsmap.shared.consultancy

import kotlinx.coroutines.launch


fun HomeViewModel.observeState(callback: (ConsultanciesState) -> Unit) {
    viewModelScope.launch {
        state.collect(callback)
    }
}