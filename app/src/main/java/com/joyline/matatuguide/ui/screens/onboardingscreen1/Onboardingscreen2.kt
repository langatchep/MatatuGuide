package com.joyline.matatuguide.ui.screens.onboardingscreen2


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
import com.joyline.matatuguide.navigation.Routes

@Composable
fun OnboardingScreenTwo(
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
            text = "Track Your Transport Usage",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1E3A8A)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Save routes, monitor fare changes and analyze your monthly transport spending easily.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {

                // NAVIGATE TO LOGIN OR HOME SCREEN
                navController.navigate(Routes.LOGIN)

            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A)
            ),

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Get Started")

        }

    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenTwoPreview() {

    OnboardingScreenTwo(
        navController = rememberNavController()
    )

}