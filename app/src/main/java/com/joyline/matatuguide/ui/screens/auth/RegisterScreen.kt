package com.joyline.matatuguide.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.joyline.matatuguide.model.User
import com.joyline.matatuguide.navigation.Routes

@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Create Account ✨",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Register to continue using MatatuGuide",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it; error = "" },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    enabled = !isLoading
                )
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it; error = "" },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    enabled = !isLoading
                )
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it; error = "" },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    enabled = !isLoading
                )
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(
                    value = confirm,
                    onValueChange = { confirm = it; error = "" },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    enabled = !isLoading
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (error.isNotEmpty()) {
            Text(text = error, color = Color.Red)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                if (name.isBlank() || email.isBlank() || password.isBlank() || confirm.isBlank()) {
                    error = "Fill all fields"
                    return@Button
                }
                if (password != confirm) {
                    error = "Passwords do not match"
                    return@Button
                }

                isLoading = true
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->
                        val uid = result.user?.uid ?: ""
                        val user = User(uid = uid, name = name, email = email)

                        db.collection("users").document(uid).set(user)
                            .addOnSuccessListener {
                                isLoading = false
                                navController.navigate(Routes.HOME) {
                                    popUpTo(Routes.REGISTER) { inclusive = true }
                                }
                            }
                            .addOnFailureListener {
                                isLoading = false
                                error = it.message ?: "Failed to save user info"
                            }
                    }
                    .addOnFailureListener {
                        isLoading = false
                        error = it.message ?: "Registration failed"
                    }
            },
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E3A8A)),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(text = "Register", color = Color.White, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = { navController.navigate(Routes.LOGIN) { launchSingleTop = true } },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = !isLoading
        ) {
            Text(text = "Already have an account? Login", color = Color(0xFF1E3A8A))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterScreen(navController = rememberNavController())
}
