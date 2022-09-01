package com.tarlad.eventsmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.tarlad.eventsmap.base.LocalViewModelProvider
import com.tarlad.eventsmap.events.EventsScreen
import com.tarlad.eventsmap.shared.Platform
import com.tarlad.eventsmap.shared.client
import com.tarlad.eventsmap.shared.events.EventsViewModel
import com.tarlad.eventsmap.shared.test
import com.tarlad.eventsmap.ui.theme.EventsMapTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventsMapTheme {
                // TODO routing
                Box(Modifier.fillMaxSize()) {
                    EventsScreen()
                }
            }
        }
    }
}