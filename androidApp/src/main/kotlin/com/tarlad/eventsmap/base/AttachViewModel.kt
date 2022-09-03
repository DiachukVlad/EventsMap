package com.tarlad.eventsmap.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.tarlad.eventsmap.shared.base.BaseViewModel
import org.koin.androidx.compose.get

@Composable
inline fun <reified T: BaseViewModel>attachViewModel(): T {
    val vm: T = get()
    DisposableEffect(Unit) {
        vm.onStart()
        onDispose {
            vm.onClose()
        }
    }
    return vm
}