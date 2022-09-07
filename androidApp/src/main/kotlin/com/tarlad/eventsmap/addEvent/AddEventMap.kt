package com.tarlad.eventsmap.addEvent

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.tarlad.eventsmap.base.toDp
import com.tarlad.eventsmap.shared.addEvent.AddEventViewModel
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.get

@Composable
fun AddEventMap(bottomPadding: Int, vm: AddEventViewModel = get()) {
    val selectedLocation by vm.selectedLocation
        .map { LatLng(it.first, it.second) }
        .collectAsState(LatLng(.0, .0))

    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false)) }
    val properties = remember { MapProperties(mapType = MapType.NORMAL) }
    val positionState = rememberCameraPositionState()

    LaunchedEffect(selectedLocation) {
        positionState.animate(CameraUpdateFactory.newLatLng(selectedLocation))
    }


    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .matchParentSize()
                .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom)),
            cameraPositionState = positionState,
            properties = properties,
            uiSettings = uiSettings,
            onMapClick = { vm.onLongClick(it.latitude, it.longitude) },
            contentPadding =
            PaddingValues(bottom = bottomPadding.toDp())
        ) {
            Marker(
                state = remember(selectedLocation) { MarkerState(selectedLocation) }
            )
        }
    }
}