package com.tarlad.eventsmap.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.tarlad.eventsmap.base.EventsBottomSheetScaffold
import com.tarlad.eventsmap.shared.home.HomeViewModel
import org.koin.androidx.compose.get
import kotlin.math.min

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EventsBottomSheet(calculatedBottomPadding: (Int) -> Unit, vm: HomeViewModel = get()) {
    val events by vm.events.collectAsState()

    EventsBottomSheetScaffold(
        topPart = {
            TopPart()
        },
        horizontalContent = {
            BoxWithConstraints(Modifier.fillMaxWidth()) {
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    count = events.size,
                ) {
                    EventsListItem(
                        event = events[it],
                        maxWidth = this@BoxWithConstraints.constraints.maxWidth
                    )
                }
            }
        },
        verticalContent = {
            LazyColumn(Modifier.fillMaxSize()){
                items(events) {event ->
                    BoxWithConstraints(
                        Modifier
                            .fillMaxWidth()
                            .height(200.dp)) {
                        EventsListItem(
                            event = event,
                            maxWidth = this@BoxWithConstraints.constraints.maxWidth
                        )
                    }
                }
            }
        },
        onOffsetChanged = {
            calculatedBottomPadding(min(it.current, it.small).toInt())
        }
    )
}

@Composable
fun TopPart() {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
            .background(MaterialTheme.colors.background)
            .padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp, 4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color.Black)
                .align(Alignment.Center)
        )
    }
}