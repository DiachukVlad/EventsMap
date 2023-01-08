package com.tarlad.eventsmap.shared.base

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel {
    val viewModelScope = CoroutineScope(Dispatchers.Default + Job())

    open fun onStart() {
    }

    open fun onClose() {
        viewModelScope.coroutineContext.cancelChildren(CancellationException("viewClosed"))
    }

    fun <T> MutableStateFlow<T>.update(update: T.() -> T) {
        viewModelScope.launch {
            this@update.emit(update(this@update.value))
        }
    }
}