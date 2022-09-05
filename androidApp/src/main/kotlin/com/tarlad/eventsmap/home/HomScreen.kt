package com.tarlad.eventsmap.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.diachuk.routing.LocalRouting
import com.tarlad.eventsmap.base.createRoute
import com.tarlad.eventsmap.events.EventsListScreen
import com.tarlad.eventsmap.shared.models.Event
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

val HomeScreen = createRoute {
    Column(Modifier.fillMaxWidth()) {



    }
}
