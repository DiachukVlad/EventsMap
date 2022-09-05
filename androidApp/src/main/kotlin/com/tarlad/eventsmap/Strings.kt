package com.tarlad.eventsmap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalStrings = compositionLocalOf { Strings() }

@Composable
fun AppStrings(content: @Composable ()->Unit) {
    CompositionLocalProvider(LocalStrings provides Strings(), content = content)
}

data class Strings(
    val search: String = "Search"
)