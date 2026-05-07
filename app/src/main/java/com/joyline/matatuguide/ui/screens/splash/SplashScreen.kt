package com.joyline.matatuguide.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.joyline.matatuguide.navigation.ROUT_HOME
import com.joyline.matatuguide.navigation.ROUT_SPLASH
import kotlinx.coroutines.delay

// ---------------- REAL SPLASH SCREEN ----------------
@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(2500)

        navController.navigate(ROUT_HOME) {
            popUpTo(ROUT_SPLASH) { inclusive = true }
            launchSingleTop = true
        }
    }

    SplashContent()
}

// ---------------- UI CONTENT (PREVIEW SAFE) ----------------
@Composable
fun SplashContent() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)), // light modern background
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🚍 ICON
            Text(
                text = "🚌",
                fontSize = 90.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 🟦 APP NAME
            Text(
                text = "MatatuGuide",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            // ✨ TAGLINE
            Text(
                text = "Smart Travel. Smarter Routes.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 🔵 LOADING INDICATOR
            CircularProgressIndicator(
                color = Color(0xFF1E3A8A),
                strokeWidth = 2.dp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashContent()
}

