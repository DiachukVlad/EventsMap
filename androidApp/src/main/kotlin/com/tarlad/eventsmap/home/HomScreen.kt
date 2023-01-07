package com.tarlad.eventsmap.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.shared.consultancy.HomeViewModel


val HomeScreen = createRoute<HomeViewModel> {
    Box(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Text(text = "Hello world", style = MaterialTheme.typography.h4)
    }
}