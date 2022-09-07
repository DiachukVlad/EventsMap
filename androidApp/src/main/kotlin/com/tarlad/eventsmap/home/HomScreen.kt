package com.tarlad.eventsmap.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.shared.home.HomeViewModel


val HomeScreen = createRoute<HomeViewModel> {
    Box(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        var bottomPadding by remember { mutableStateOf(0) }

        EventsMap(bottomPadding)
        Search()
        EventsBottomSheet(calculatedBottomPadding = { bottomPadding = it })
    }
}