package com.joyline.matatuguide.ui.screens.maps

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.*

@Composable
fun MapScreen(
    start: String,
    end: String
) {

    // Nairobi Coordinates (Default center)
    val nairobi = LatLng(-1.286389, 36.817223)

    // Mock Destination (e.g., somewhere in Westlands if center is CBD)
    val destination = LatLng(-1.2635, 36.8041)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            nairobi,
            14f
        )
    }

    // Map properties to enable current location
    val uiSettings by remember { mutableStateOf(MapUiSettings(myLocationButtonEnabled = true)) }
    val properties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }

    Box(modifier = Modifier.fillMaxSize()) {
        
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ) {
            // Destination Marker
            Marker(
                state = MarkerState(position = destination),
                title = "Destination: $end",
                snippet = "Arriving soon",
                icon = null // You could use a custom icon here
            )
        }

        // Overlay for Time and Distance
        Card(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
                .fillMaxWidth(0.9f),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.9f)
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Distance Info
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF1E3A8A)
                    )
                    Text(
                        text = "2.4 km",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Remaining",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                // Divider
                VerticalDivider(modifier = Modifier.height(40.dp), thickness = 1.dp, color = Color.LightGray)

                // Time Info
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = null,
                        tint = Color(0xFF1E3A8A)
                    )
                    Text(
                        text = "12 mins",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Est. Time",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        // Bottom Banner for Route Info
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = Color(0xFF1E3A8A),
            tonalElevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Route: $start → $end",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(start = "Nairobi CBD", end = "Westlands")
}
