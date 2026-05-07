package com.joyline.matatuguide.ui.screens.saved

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joyline.matatuguide.data.RouteStorage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedRoutesScreen() {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var routes by remember { mutableStateOf(listOf<String>()) }

    // Load routes
    LaunchedEffect(Unit) {
        routes = RouteStorage.getRoutes(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Saved Routes") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            if (routes.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        text = "No saved routes yet",
                        color = Color.Gray
                    )
                }

            } else {

                LazyColumn {

                    items(
                        items = routes,
                        key = { it } // ✅ important fix
                    ) { route ->

                        val parts = route.split("|")

                        if (parts.size == 4) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(6.dp)
                            ) {

                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Column {

                                        Text(
                                            text = "${parts[0]} → ${parts[1]}",
                                            color = Color(0xFF1E3A8A),
                                            style = MaterialTheme.typography.titleMedium
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        Text(
                                            text = "Stages: ${parts[2]}",
                                            color = Color.Gray
                                        )

                                        Text(
                                            text = "Fare: ${parts[3]}",
                                            color = Color(0xFF1E3A8A)
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            scope.launch {

                                                val updated = routes.toMutableList()
                                                updated.remove(route)

                                                RouteStorage.overwriteRoutes(
                                                    context,
                                                    updated
                                                )

                                                routes = updated
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRoutesPreview() {
    SavedRoutesScreen()
}