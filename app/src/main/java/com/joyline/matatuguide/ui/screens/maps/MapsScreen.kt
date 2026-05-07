package com.joyline.matatuguide.ui.screens.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

/* ---------------- REAL MAP ---------------- */

@Composable
fun MapScreen(
    start: String,
    end: String
) {

    val fromLocation = LatLng(-1.286389, 36.817223) // Nairobi
    val toLocation = LatLng(-1.3733, 36.8580)       // Athi River

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(fromLocation, 12f)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🗺️ MAP
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {

            Marker(
                state = MarkerState(position = fromLocation),
                title = start
            )

            Marker(
                state = MarkerState(position = toLocation),
                title = end
            )

            Polyline(points = listOf(fromLocation, toLocation))
        }

        // 🔵 TOP UBER BAR
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
                .shadow(10.dp, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(modifier = Modifier.padding(14.dp)) {

                Text(
                    text = "From: $start",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "To: $end",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // 🔻 BOTTOM UBER PANEL
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .shadow(12.dp, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Trip Details",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1E3A8A)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Route: $start → $end")
                Text("Estimated Fare: KES 100 - 150")
                Text("ETA: 25 - 40 mins")

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E3A8A)
                    )
                ) {
                    Text("Start Trip")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE5E7EB))
    ) {

        // 🗺️ Fake map preview UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD1D5DB)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🗺️ Map Preview\nNairobi → Athi River",
                color = Color.Black
            )
        }

        // 🔵 TOP BAR PREVIEW
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("From: Nairobi")
                Text("To: Athi River")
            }
        }

        // 🔻 BOTTOM PANEL PREVIEW
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Trip Details",
                    color = Color(0xFF1E3A8A)
                )

                Text("Route: Nairobi → Athi River")
                Text("Fare: KES 100 - 150")

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start Trip")
                }
            }
        }
    }
}