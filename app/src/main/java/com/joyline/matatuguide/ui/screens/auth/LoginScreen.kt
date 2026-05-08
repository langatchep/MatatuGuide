package com.joyline.matatuguide.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.navigation.Routes

@Composable
fun LoginScreen(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 HEADER
        Text(
            text = "Welcome Back 👋",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Login to continue using MatatuGuide",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        // 📦 LOGIN CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        error = ""
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        error = ""
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // ❌ ERROR MESSAGE
        if (error.isNotEmpty()) {

            Text(
                text = error,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        // 🔐 LOGIN BUTTON
        Button(
            onClick = {

                if (email.isBlank() || password.isBlank()) {

                    error = "Fill all fields"

                } else {

                    navController.navigate(Routes.HOME) {

                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A)
            )
        ) {

            Text(
                text = "Login",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 📝 REGISTER
        TextButton(
            onClick = {

                navController.navigate(Routes.REGISTER) {
                    launchSingleTop = true
                }
            },

            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

            Text(
                text = "No account? Register",
                color = Color(0xFF1E3A8A)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {

    LoginScreen(
        navController = rememberNavController()
    )
}