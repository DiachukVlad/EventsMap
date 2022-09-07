package com.tarlad.eventsmap.addEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import com.diachuk.routing.LocalRouting
import com.tarlad.eventsmap.base.BottomSheetTopPart
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.base.toDp
import com.tarlad.eventsmap.shared.addEvent.AddEventViewModel

val AddEventScreen = createRoute<AddEventViewModel> { vm ->
    val name by vm.name.collectAsState()
    val routing = LocalRouting

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        var bottomPadding by remember { mutableStateOf(0) }
        var topBarSize by remember { mutableStateOf(0) }

        Box(Modifier.fillMaxWidth()) {
            AddEventMap(bottomPadding+topBarSize)
            BottomSheetTopPart(
                Modifier
                    .systemBarsPadding()
                    .padding(bottom = bottomPadding.toDp())
                    .align(Alignment.BottomCenter)
                    .onSizeChanged { topBarSize = it.height })
        }

        Column(
            Modifier
                .systemBarsPadding()
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colors.background)
                .onSizeChanged { bottomPadding = it.height }
        ) {
            TextField(value = name, onValueChange = vm::onNameChange, label = { Text(text = "Name") })
            Button(onClick = {
                vm.addEvent(onDone = {
                    routing.pop()
                })
            }) {
                Text(text = "Add")
            }
        }

    }
}
