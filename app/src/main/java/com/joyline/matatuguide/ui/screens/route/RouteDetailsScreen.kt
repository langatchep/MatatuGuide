package com.joyline.matatuguide.ui.screens.details

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.data.RouteStorage
import com.joyline.matatuguide.navigation.Routes
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteDetailsScreen(
    navController: NavHostController,
    start: String,
    end: String,
    stages: String,
    fare: String
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // --- Dynamic Fare Logic ---
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val isRushHour = currentHour in 6..9 || currentHour in 16..19
    
    val baseFare = fare.toIntOrNull() ?: 0
    val rushHourFare = (baseFare * 1.5).toInt() // 50% increase during rush hour
    val activeFare = if (isRushHour) rushHourFare else baseFare

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Route Details", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A))
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            // Destination Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4FF))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "From: $start",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "To: $end",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A8A)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Fare Information Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color(0xFF3B82F6))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isRushHour) "Currently: Rush Hour" else "Currently: Normal Hour",
                            fontWeight = FontWeight.SemiBold,
                            color = if (isRushHour) Color.Red else Color(0xFF2E7D32)
                        )
                    }
                    
                    Divider(modifier = Modifier.padding(vertical = 12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Normal Fare", fontSize = 12.sp, color = Color.Gray)
                            Text("KES $baseFare", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Rush Hour Fare", fontSize = 12.sp, color = Color.Gray)
                            Text("KES $rushHourFare", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(
                        color = Color(0xFF1E3A8A),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "ACTIVE FARE: KES $activeFare",
                            color = Color.White,
                            modifier = Modifier.padding(12.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Additional Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFF1E3A8A))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Estimated Stages: $stages", color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Actions
            Button(
                onClick = {
                    navController.navigate(Routes.maps(Uri.encode(start), Uri.encode(end)))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Default.Map, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("View Live Map")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    scope.launch {
                        RouteStorage.saveRoute(
                            context,
                            listOf(start, end, stages, activeFare.toString()).joinToString("|")
                        )
                        Toast.makeText(context, "Route Saved with current fare!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Default.Save, contentDescription = null, tint = Color(0xFF1E3A8A))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save Route Details", color = Color(0xFF1E3A8A))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RouteDetailsPreview() {
    RouteDetailsScreen(
        navController = rememberNavController(),
        start = "Nairobi CBD",
        end = "Athi River",
        stages = "12",
        fare = "100"
    )
}
