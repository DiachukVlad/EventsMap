package com.tarlad.eventsmap.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.shared.consultancy.HomeViewModel


val HomeScreen = createRoute<HomeViewModel> { vm ->
    val state by vm.state.collectAsState()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .systemBarsPadding()
    ) {
        items(state.consultancies) {
            Text(text = it.name, style = MaterialTheme.typography.h4)
        }
    }
}