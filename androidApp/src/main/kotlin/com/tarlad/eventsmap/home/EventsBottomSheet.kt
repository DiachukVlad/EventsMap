package com.tarlad.eventsmap.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.tarlad.eventsmap.base.BottomSheetTopPart
import com.tarlad.eventsmap.base.EventsBottomSheetScaffold
import com.tarlad.eventsmap.base.EventsBottomSheetState
import com.tarlad.eventsmap.shared.home.HomeViewModel
import org.koin.androidx.compose.get
import kotlin.math.min

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EventsBottomSheet(calculatedBottomPadding: (Int) -> Unit, vm: HomeViewModel = get()) {
    val events by vm.events.collectAsState()
    val sheetState = rememberSwipeableState(initialValue = EventsBottomSheetState.Small)
    val horizontalAlpha by animateFloatAsState(
        targetValue =
        if (sheetState.targetValue == EventsBottomSheetState.Collapsed)
            0f
        else
            1f
    )

    EventsBottomSheetScaffold(
        sheetState = sheetState,
        topPart = {
            BottomSheetTopPart()
        },
        horizontalContent = {
            BoxWithConstraints(Modifier.fillMaxWidth()) {
                HorizontalPager(
                    modifier = Modifier
                        .alpha(horizontalAlpha)
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
            LazyColumn(Modifier.fillMaxSize()) {
                items(events) { event ->
                    BoxWithConstraints(
                        Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
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