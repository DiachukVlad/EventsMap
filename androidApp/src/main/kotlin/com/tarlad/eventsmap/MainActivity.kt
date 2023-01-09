package com.tarlad.eventsmap

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.diachuk.routing.Routing
import com.diachuk.routing.RoutingHost
import com.tarlad.eventsmap.auth.AuthScreen
import com.tarlad.eventsmap.ui.theme.HireMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            HireMateTheme {
                AppStrings {
                    RoutingHost(routing = Routing(AuthScreen))
                }
            }
        }
    }
}