package com.tarlad.eventsmap.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetTopPart(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
            .background(MaterialTheme.colors.background)
            .padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp, 4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colors.onBackground)
                .align(Alignment.Center)
        )
    }
}