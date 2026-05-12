package com.joyline.matatuguide.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun OnboardingScreenOne(
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Welcome to MatatuGuide",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1E3A8A)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Find matatu routes, boarding stages, fare information and navigate easily across Kenya.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {

                // NAVIGATE TO SECOND ONBOARDING SCREEN
                // Example:
                // navController.navigate(Routes.onboarding2)

            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A)
            ),

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Next")

        }

    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenOnePreview() {

    OnboardingScreenOne(
        navController = rememberNavController()
    )

}