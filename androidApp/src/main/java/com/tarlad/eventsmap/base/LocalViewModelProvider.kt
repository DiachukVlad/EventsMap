package com.tarlad.eventsmap.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import com.tarlad.eventsmap.shared.base.BaseViewModel

@Composable
fun <T: BaseViewModel>LocalViewModelProvider(vm: T, localComposition: ProvidableCompositionLocal<T>, content: @Composable ()->Unit){
    DisposableEffect(Unit) {
        vm.onStart()
        onDispose {
            vm.onClose()
        }
    }
    CompositionLocalProvider(localComposition provides vm, content = content)
}