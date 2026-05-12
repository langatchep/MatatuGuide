package com.joyline.matatuguide.ui.screens.home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF8FAFC), Color(0xFFE2E8F0))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // User Welcome Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hello, Commuter! 👋",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A8A)
                    )
                    Text(
                        text = "Where are we going today?",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1E3A8A)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Search Bar
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = {},
                active = false,
                onActiveChange = {},
                placeholder = { Text("Search routes or stages...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = SearchBarDefaults.colors(containerColor = Color.White)
            ) {}

            Spacer(modifier = Modifier.height(24.dp))

            // Map Preview Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Live Traffic Map",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1F2937)
                        )
                        Surface(
                            color = Color(0xFFDCFCE7),
                            shape = CircleShape
                        ) {
                            Text(
                                text = "Smooth",
                                color = Color(0xFF166534),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF1F5F9)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Map, modifier = Modifier.size(50.dp), tint = Color(0xFF94A3B8), contentDescription = null)
                        Text("Map Preview Placeholder", modifier = Modifier.align(Alignment.BottomCenter).padding(8.dp), fontSize = 10.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        QuickActionButton(icon = Icons.Default.Navigation, label = "Directions")
                        QuickActionButton(icon = Icons.Default.BusAlert, label = "Incidents")
                        QuickActionButton(icon = Icons.Default.Update, label = "Updates")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Route Finder Card
            Text(text = "Plan Your Trip", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF1E3A8A))
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = from,
                        onValueChange = { from = it },
                        label = { Text("Starting point") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.MyLocation, contentDescription = null, tint = Color(0xFF3B82F6)) },
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = to,
                        onValueChange = { to = it },
                        label = { Text("Destination") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFEF4444)) },
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            if (from.isNotBlank() && to.isNotBlank()) {
                                navController.navigate("results/${Uri.encode(from)}/${Uri.encode(to)}")
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E3A8A))
                    ) {
                        Text("Find Best Routes", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp)) // Padding for bottom nav
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { navController.navigate(Routes.SAVED) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            containerColor = Color(0xFF1E3A8A),
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Bookmark, contentDescription = "Saved Routes")
        }
    }
}

@Composable
fun QuickActionButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF1F5F9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = Color(0xFF1E3A8A))
        }
        Text(text = label, fontSize = 11.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(rememberNavController())
}
