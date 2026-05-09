package com.joyline.matatuguide.ui.screens.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    start: String,
    end: String
) {

    // Nairobi Coordinates
    val nairobi = LatLng(-1.286389, 36.817223)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            nairobi,
            12f
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

        Marker(
            state = MarkerState(position = nairobi),
            title = start,
            snippet = end
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {

}