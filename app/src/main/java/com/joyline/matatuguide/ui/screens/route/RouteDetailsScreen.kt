package com.joyline.matatuguide.ui.screens.details

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.data.RouteStorage
import com.joyline.matatuguide.navigation.Routes
import kotlinx.coroutines.launch

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

        Text(
            text = "Stages: $stages",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Fare: $fare",
            color = Color(0xFF3B82F6)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 🗺️ VIEW ON MAP
        Button(
            onClick = {

                navController.navigate(
                    Routes.maps(
                        Uri.encode(start),
                        Uri.encode(end)
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3B82F6)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View on Map")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 💾 SAVE ROUTE
        Button(
            onClick = {

                scope.launch {

                    RouteStorage.saveRoute(
                        context,
                        listOf(
                            start,
                            end,
                            stages,
                            fare
                        ).joinToString("|")
                    )

                    Toast.makeText(
                        context,
                        "Route Saved!",
                        Toast.LENGTH_SHORT
                    ).show()
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

@Preview(showBackground = true)
@Composable
fun RouteDetailsPreview() {

    RouteDetailsScreen(
        navController = rememberNavController(),
        start = "Nairobi",
        end = "Athi River",
        stages = "6",
        fare = "100"
    )
}