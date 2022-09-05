package com.tarlad.eventsmap.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tarlad.eventsmap.shared.models.Event

@Composable
fun EventsListItem(event: Event, maxWidth: Int) {
    Surface(Modifier
        .width(with(LocalDensity.current) { maxWidth.toDp() })
        .padding(24.dp), elevation = 6.dp, shape = RoundedCornerShape(24.dp)) {
        Row(
            Modifier
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .background(Color.DarkGray)
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(event.toString())
            }
        }
    }
}

@Preview
@Composable
fun EPrev() {
    EventsListItem(Event("qwekhjdsd"), 700)
}