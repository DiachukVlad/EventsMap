package com.tarlad.eventsmap.base

import androidx.compose.runtime.Composable
import com.diachuk.routing.Route
import com.tarlad.eventsmap.shared.base.BaseViewModel

inline fun <reified T:BaseViewModel>createRoute(crossinline content: @Composable (T)->Unit) = object: Route() {
    @Composable
    override fun Content() {
        content(attachViewModel())
    }
}

fun createRoute(content: @Composable ()->Unit) = object: Route() {
    @Composable
    override fun Content() {
        content()
    }
}