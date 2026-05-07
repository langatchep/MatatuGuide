package com.joyline.matatuguide.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joyline.matatuguide.data.RouteStorage
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "$start → $end",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF1E3A8A)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Stages: $stages", color = Color.Gray)

        Spacer(modifier = Modifier.height(5.dp))

        Text("Fare: $fare", color = Color(0xFF3B82F6))

        Spacer(modifier = Modifier.height(30.dp))

        // 🗺️ VIEW ON MAP BUTTON
        Button(
            onClick = {
                navController.navigate("map/$start/$end")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3B82F6)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View on Map")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 💾 SAVE BUTTON
        Button(
            onClick = {
                scope.launch {
                    RouteStorage.saveRoute(
                        context,
                        listOf(start, end, stages, fare).joinToString("|")
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Route")
        }
    }
}
