package com.joyline.matatuguide.ui.screens.analytics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.joyline.matatuguide.model.SavedRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen() {
    val db = FirebaseFirestore.getInstance()
    var routes by remember { mutableStateOf(listOf<SavedRoutes>()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        db.collection("savedRoutes")
            .get()
            .addOnSuccessListener { result ->
                val savedList = result.documents.mapNotNull { it.toObject(SavedRoutes::class.java) }
                routes = savedList
                isLoading = false
            }
            .addOnFailureListener { isLoading = false }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Insights & Analytics", fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A))
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF1E3A8A))
            }
        } else {
            AnalyticsContent(routes = routes, modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun AnalyticsContent(routes: List<SavedRoutes>, modifier: Modifier = Modifier) {
    val totalTrips = routes.size
    val totalSpent = routes.sumOf { it.fare.toIntOrNull() ?: 0 }
    val averageFare = if (routes.isNotEmpty()) totalSpent / routes.size else 0

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Summary Cards
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard("Total Trips", "$totalTrips", Icons.Default.TrendingUp, Color(0xFF3B82F6), Modifier.weight(1f))
            StatCard("Total Spend", "KES $totalSpent", Icons.Default.BarChart, Color(0xFF1E3A8A), Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Spending Chart Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Weekly Spending Analysis", fontWeight = FontWeight.Bold, color = Color(0xFF1F2937))
                Spacer(modifier = Modifier.height(20.dp))
                
                // Simple Bar Chart using Canvas
                SpendingChart(listOf(120f, 450f, 300f, 600f, 200f, 400f, 550f))
                
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ArrowUpward, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(16.dp))
                    Text(text = "12% more than last week", fontSize = 12.sp, color = Color(0xFF10B981), fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Average Fare Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4FF))
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(50.dp).background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Info, tint = Color(0xFF1E3A8A), contentDescription = null)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Average Trip Cost", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "KES $averageFare", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E3A8A))
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun StatCard(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = label, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun SpendingChart(data: List<Float>) {
    val maxVal = data.maxOrNull() ?: 1f
    Canvas(modifier = Modifier.fillMaxWidth().height(150.dp)) {
        val width = size.width
        val height = size.height
        val barWidth = width / (data.size * 2f)
        
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxVal) * height
            drawRoundRect(
                color = Color(0xFF3B82F6),
                topLeft = Offset(x = (index * 2f + 0.5f) * barWidth, y = height - barHeight),
                size = Size(width = barWidth, height = barHeight),
                cornerRadius = CornerRadius(4.dp.toPx())
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalyticsScreenPreview() {
    val mockRoutes = listOf(
        SavedRoutes(fare = "100"),
        SavedRoutes(fare = "150"),
        SavedRoutes(fare = "200")
    )
    AnalyticsContent(routes = mockRoutes)
}
