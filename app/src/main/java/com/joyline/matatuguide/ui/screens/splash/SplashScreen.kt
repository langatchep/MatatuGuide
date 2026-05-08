package com.joyline.matatuguide.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.joyline.matatuguide.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(2500)

        navController.navigate(Routes.HOME) {
            popUpTo(Routes.SPLASH) { inclusive = true }
            launchSingleTop = true
        }
    }

    SplashContent()
}

@Composable
fun SplashContent() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text("🚌", fontSize = 90.sp)

            Spacer(Modifier.height(18.dp))

            Text(
                text = "MatatuGuide",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A)
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Smart Travel. Smarter Routes.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(30.dp))

            CircularProgressIndicator(
                color = Color(0xFF1E3A8A),
                strokeWidth = 2.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    SplashContent()
}