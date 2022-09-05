package com.tarlad.eventsmap.base

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

enum class EventsBottomSheetState {
    Collapsed, Small, FullScreen
}

data class BottomSheetOffsets(
    val top: Float,
    val small: Float,
    val current: Float
)

@Composable
fun EventsBottomSheetScaffold(
    sheetState: SwipeableState<EventsBottomSheetState> =
        rememberSwipeableState(initialValue = EventsBottomSheetState.Small),
    topPart: @Composable BoxScope.() -> Unit,
    horizontalContent: @Composable () -> Unit,
    verticalContent: @Composable () -> Unit,
    onOffsetChanged: (BottomSheetOffsets) -> Unit
) {
    val offset = sheetState.offset.value.roundToInt()
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.currentValue == EventsBottomSheetState.FullScreen) {
        coroutineScope.launch {
            sheetState.animateTo(EventsBottomSheetState.Small)
        }
    }


    BoxWithConstraints(
        modifier = Modifier
            .offset { IntOffset(0, offset) }
            .fillMaxSize()
    ) {
        var topPartHeight by remember { mutableStateOf(0f) }
        var smallPartHeight by remember { mutableStateOf(0f) }
        val maxHeight = constraints.maxHeight.toFloat()

        val topPartOffset = maxHeight - topPartHeight
        val smallOffset = maxHeight - smallPartHeight - topPartHeight

        val anchors = mapOf(
            topPartOffset to EventsBottomSheetState.Collapsed,
            smallOffset to EventsBottomSheetState.Small,
            0f to EventsBottomSheetState.FullScreen,
        )

        LaunchedEffect(offset, topPartOffset, smallOffset) {
            onOffsetChanged(
                BottomSheetOffsets(
                    maxHeight - topPartOffset,
                    maxHeight - smallOffset,
                    maxHeight - offset
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = sheetState,
                    anchors = anchors,
                    orientation = Orientation.Vertical,
                    resistance = null
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { topPartHeight = it.height.toFloat() },
                content = topPart
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                Crossfade(
                    targetState = offset > smallOffset - 200,
                    animationSpec = tween(easing = CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f))
                ) { isSmallScreen ->
                    if (isSmallScreen) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .onSizeChanged {
                                smallPartHeight = it.height.toFloat()
                            }
                        ) {
                            horizontalContent()
                        }
                    } else {
                        verticalContent()
                    }
                }

            }
        }
    }
}