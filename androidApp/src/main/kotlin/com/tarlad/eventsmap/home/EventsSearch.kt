package com.tarlad.eventsmap.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tarlad.eventsmap.LocalStrings

@Composable
fun Search() {
    val strings = LocalStrings.current
    var searchText by remember { mutableStateOf("") }
    var focused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.surface)
            .padding(12.dp)
    ) {
        if (searchText.isEmpty() && !focused) {
            Text(text = strings.search, style = MaterialTheme.typography.h5, color = Color.Black.copy(alpha = 0.5f))
        }
        BasicTextField(
            modifier = Modifier.onFocusEvent { focused = it.isFocused },
            value = searchText,
            onValueChange = { searchText = it },
            textStyle = MaterialTheme.typography.h5
        )
    }
}