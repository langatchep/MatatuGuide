package com.joyline.matatuguide.ui.screens.home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.navigation.Routes

@Composable
fun HomeScreen(navController: NavHostController) {

    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // 🚌 APP HEADER
        Text(
            text = "🚌 MatatuGuide",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Find smarter matatu routes easily",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 📦 INPUT CARD
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
                    value = from,
                    onValueChange = {
                        from = it
                        error = ""
                    },
                    label = { Text("From") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = to,
                    onValueChange = {
                        to = it
                        error = ""
                    },
                    label = { Text("To") },
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

        // 🔍 SEARCH BUTTON
        Button(
            onClick = {
                if (from.isBlank() || to.isBlank()) {

                    error = "Enter both locations"

                } else {

                    navController.navigate(
                        "results/${Uri.encode(from)}/${Uri.encode(to)}"
                    )
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
                text = "Find Route",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 📝 REGISTER BUTTON
        OutlinedButton(
            onClick = {
                navController.navigate(Routes.REGISTER) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),

            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "Create Account",
                color = Color(0xFF1E3A8A)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔐 LOGIN
        TextButton(
            onClick = {
                navController.navigate(Routes.LOGIN) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

            Text(
                text = "Already have an account? Login",
                color = Color(0xFF1E3A8A)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 💡 INFO CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE0ECFF)
            ),
            shape = RoundedCornerShape(18.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "💡 Travel Tip",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A8A)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Travel early to avoid peak fares and traffic delays.",
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(rememberNavController())
}