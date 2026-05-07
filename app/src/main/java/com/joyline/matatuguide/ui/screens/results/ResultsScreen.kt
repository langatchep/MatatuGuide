package com.joyline.matatuguide.ui.screens.results

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


data class Route(
    val start: String,
    val end: String,
    val stages: String,
    val fare: String
)

@Composable
fun ResultsScreen(
    navController: NavHostController,
    from: String,
    to: String
) {

    val routes = listOf(
        Route(from, to, "CBD → Mlolongo → Athi River", "KES 100 - 150"),
        Route(from, to, "CBD → Syokimau → Kitengela", "KES 120 - 180")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Routes from $from to $to",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF1E3A8A)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(routes) { route ->
                RouteCard(route, navController)
            }
        }
    }
}

@Composable
fun RouteCard(
    route: Route,
    navController: NavHostController
) {

    Card(
        onClick = {
            navController.navigate(
                "details/${route.start}/${route.end}/${route.stages}/${route.fare}"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "${route.start} → ${route.end}",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1E3A8A)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Stages: ${route.stages}",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Fare: ${route.fare}",
                color = Color(0xFF1E3A8A)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RouteCardPreview() {
    RouteCard(
        route = Route(
            "CBD",
            "Athi River",
            "CBD → Mlolongo → Athi River",
            "KES 100 - 150"
        ),
        navController = rememberNavController()
    )
}