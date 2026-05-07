package com.joyline.matatuguide.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.navigation.ROUT_HOME
import com.joyline.matatuguide.navigation.ROUT_REGISTER

@Composable
fun LoginScreen(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Welcome Back 👋",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1E3A8A)
        )

        Text(
            text = "Login to continue",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(25.dp))

        // INPUT CARD
        Card(
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        error = ""
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        error = ""
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // LOGIN BUTTON
        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    error = "Please enter email and password"
                } else {
                    navController.navigate(ROUT_HOME) {
                        popUpTo(navController.graph.startDestinationId)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // GO TO REGISTER
        TextButton(
            onClick = {
                navController.navigate(ROUT_REGISTER)
            }
        ) {
            Text("Don't have an account? Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}