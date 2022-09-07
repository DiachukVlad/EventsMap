package com.tarlad.eventsmap.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.diachuk.routing.LocalRouting
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.tarlad.eventsmap.addEvent.AddEventScreen
import com.tarlad.eventsmap.base.toDp
import com.tarlad.eventsmap.shared.addEvent.AddEventViewModel
import com.tarlad.eventsmap.shared.home.HomeViewModel
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.get

@Composable
fun EventsMap(bottomPadding: Int, vm: HomeViewModel = get()) {
    val events by vm.events.collectAsState()
    val tappedLoc by vm.tappedLocation
        .map { it?.let { LatLng(it.first, it.second) } }
        .collectAsState(null)

    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false)) }
    val properties = remember { MapProperties(mapType = MapType.NORMAL) }
    val positionState = rememberCameraPositionState()

    LaunchedEffect(tappedLoc) {
        tappedLoc?.let {
            positionState.animate(CameraUpdateFactory.newLatLng(it))
        }
    }

    val focusManager = LocalFocusManager.current

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .matchParentSize()
                .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom)),
            cameraPositionState = positionState,
            properties = properties,
            uiSettings = uiSettings,
            onMapClick = { focusManager.clearFocus() },
            onMapLongClick = { vm.onLongClick(it.latitude, it.longitude) },
            contentPadding =
            PaddingValues(bottom = bottomPadding.toDp())
        ) {
            tappedLoc?.let {
                Marker(
                    state = remember(tappedLoc) { MarkerState(it) }
                )
            }
            events.forEach {
                Marker(
                    state = remember {
                        MarkerState(LatLng(it.lat, it.lon))
                    }
                )
            }
        }

        if (tappedLoc != null) AddEventButton(bottomPadding)
    }
}

@Composable
fun BoxScope.AddEventButton(bottomPadding: Int) {
    val routing = LocalRouting
    val vm: HomeViewModel = get()
    val addEventViewModel: AddEventViewModel = get()

    FloatingActionButton(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .systemBarsPadding()
            .padding(bottom = bottomPadding.toDp() + 16.dp, end = 16.dp),
        onClick = {
            vm.tappedLocation.value?.let { addEventViewModel.selectedLocation.tryEmit(it) }
            routing.push(AddEventScreen)
        }
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add event")
    }
}