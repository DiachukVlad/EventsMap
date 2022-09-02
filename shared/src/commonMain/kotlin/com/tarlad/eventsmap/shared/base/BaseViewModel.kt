package com.tarlad.eventsmap.shared.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel {
    val viewModelScope = CoroutineScope(Dispatchers.Default + Job())

    open fun onStart() {

    }

    open fun onClose() {
        viewModelScope.cancel()
    }
}