package com.tarlad.eventsmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.diachuk.routing.Routing
import com.diachuk.routing.RoutingHost
import com.tarlad.eventsmap.home.HomeScreen
import com.tarlad.eventsmap.ui.theme.EventsMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventsMapTheme {
                AppStrings {
                    RoutingHost(routing = Routing(HomeScreen))
                }
            }
        }
    }
}