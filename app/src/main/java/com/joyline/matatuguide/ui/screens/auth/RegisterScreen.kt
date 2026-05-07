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
import com.joyline.matatuguide.navigation.ROUT_LOGIN

@Composable
fun RegisterScreen(navController: NavHostController) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Create Account ✨",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1E3A8A)
        )

        Text(
            text = "Register to get started",
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
                    value = name,
                    onValueChange = {
                        name = it
                        error = ""
                    },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

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

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        error = ""
                    },
                    label = { Text("Confirm Password") },
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

        // REGISTER BUTTON
        Button(
            onClick = {
                when {
                    name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                        error = "All fields are required"
                    }
                    password != confirmPassword -> {
                        error = "Passwords do not match"
                    }
                    else -> {
                        // TODO: connect Firebase Auth later
                        navController.navigate(ROUT_HOME) {
                            popUpTo(navController.graph.startDestinationId)
                        }
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
            Text("Register")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // GO TO LOGIN
        TextButton(
            onClick = {
                navController.navigate(ROUT_LOGIN)
            }
        ) {
            Text("Already have an account? Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}