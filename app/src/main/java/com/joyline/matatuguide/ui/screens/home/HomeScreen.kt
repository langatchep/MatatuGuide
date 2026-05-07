package com.joyline.matatuguide.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.navigation.*

// ---------------- REAL SCREEN (USES NAVIGATION) ----------------
@Composable
fun HomeScreen(navController: NavHostController) {

    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    HomeContent(
        from = from,
        to = to,
        error = error,

        onFromChange = {
            from = it
            error = ""
        },

        onToChange = {
            to = it
            error = ""
        },

        onSearch = {
            if (from.isBlank() || to.isBlank()) {
                error = "Please enter both locations"
            } else {
                navController.navigate(resultsRoute(from, to))
            }
        },

        onRegister = {
            navController.navigate(ROUT_REGISTER)
        },

        onLogin = {
            navController.navigate(ROUT_LOGIN)
        }
    )
}

// ---------------- UI CONTENT (PREVIEW SAFE) ----------------
@Composable
fun HomeContent(
    from: String,
    to: String,
    error: String,
    onFromChange: (String) -> Unit,
    onToChange: (String) -> Unit,
    onSearch: () -> Unit,
    onRegister: () -> Unit,
    onLogin: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // 🔵 HEADER
        Text(
            text = "🚌 MatatuGuide",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1E3A8A)
        )

        Text(
            text = "Where are you going?",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 🧾 INPUT CARD
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(modifier = Modifier.padding(16.dp)) {

                OutlinedTextField(
                    value = from,
                    onValueChange = onFromChange,
                    label = { Text("From") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = to,
                    onValueChange = onToChange,
                    label = { Text("To") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // ❗ ERROR
        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 🚀 SEARCH BUTTON
        Button(
            onClick = onSearch,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text("Find Route")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 📝 REGISTER
        OutlinedButton(
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }

        Spacer(modifier = Modifier.height(5.dp))

        // 🔐 LOGIN
        TextButton(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 💡 INFO CARD
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "💡 Travel Tip",
                    color = Color(0xFF1E3A8A)
                )

                Text(
                    text = "Travel early to avoid peak fares.",
                    color = Color.Gray
                )
            }
        }
    }
}

// ---------------- PREVIEW ----------------
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeContent(
        from = "Nairobi",
        to = "Athi River",
        error = "",
        onFromChange = {},
        onToChange = {},
        onSearch = {},
        onRegister = {},
        onLogin = {}
    )
}

// ---------------- PREVIEW WITH ERROR ----------------
@Preview(showBackground = true)
@Composable
fun HomePreviewError() {
    HomeContent(
        from = "",
        to = "",
        error = "Please enter both locations",
        onFromChange = {},
        onToChange = {},
        onSearch = {},
        onRegister = {},
        onLogin = {}
    )
}