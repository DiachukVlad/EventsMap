package com.tarlad.eventsmap.shared.base

import kotlinx.coroutines.*

open class BaseViewModel {
    val viewModelScope = CoroutineScope(Dispatchers.Default + Job())

    open fun onStart() {
    }

    open fun onClose() {
        viewModelScope.coroutineContext.cancelChildren(CancellationException("viewClosed"))
    }
}