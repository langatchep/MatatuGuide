package com.joyline.matatuguide.ui.screens.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
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
import com.joyline.matatuguide.data.RouteStorage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedRoutesScreen(initialRoutes: List<String>? = null) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var routes by remember { mutableStateOf(initialRoutes ?: listOf<String>()) }

    LaunchedEffect(Unit) {
        if (initialRoutes == null) {
            routes = RouteStorage.getRoutes(context)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Saved Routes", fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A))
            )
        },
        containerColor = Color(0xFFF8FAFC)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (routes.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.DirectionsBus, contentDescription = null, modifier = Modifier.size(80.dp), tint = Color.LightGray)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "No saved routes yet", color = Color.Gray, fontWeight = FontWeight.Medium)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(routes, key = { it }) { route ->
                        val parts = route.split("|")
                        if (parts.size >= 4) {
                            SavedRouteCard(
                                start = parts[0],
                                end = parts[1],
                                stages = parts[2],
                                fare = parts[3],
                                onDelete = {
                                    scope.launch {
                                        val updated = routes.toMutableList()
                                        updated.remove(route)
                                        RouteStorage.overwriteRoutes(context, updated)
                                        routes = updated
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SavedRouteCard(start: String, end: String, stages: String, fare: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(45.dp).background(Color(0xFFF0F4FF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.LocationOn, tint = Color(0xFF1E3A8A), contentDescription = null)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "$start → $end", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A), fontSize = 16.sp)
                Text(text = "Stages: $stages", fontSize = 12.sp, color = Color.Gray)
                Text(text = "Fare: KES $fare", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF3B82F6))
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, tint = Color(0xFFEF4444), contentDescription = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRoutesPreview() {
    val mockData = listOf(
        "Nairobi|Mombasa|Voi, Mtito Andei|1500",
        "Eldoret|Kisumu|Kapsabet, Chavakali|500"
    )
    SavedRoutesScreen(initialRoutes = mockData)
}
