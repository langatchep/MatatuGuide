package com.joyline.matatuguide.ui.screens.admin

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.joyline.matatuguide.model.User
import com.joyline.matatuguide.ui.theme.MatatuGuideTheme

@Composable
fun AdminDashboardScreen() {
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    var users by remember { mutableStateOf(listOf<User>()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch users from Firestore
    LaunchedEffect(Unit) {
        db.collection("users")
            .addSnapshotListener { value, error ->
                isLoading = false
                if (error != null) {
                    Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                val userList = value?.documents?.mapNotNull { it.toObject(User::class.java) } ?: emptyList()
                users = userList
            }
    }

    AdminDashboardContent(
        users = users,
        isLoading = isLoading,
        onDeleteUser = { user ->
            db.collection("users").document(user.uid).delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "User deleted from record", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to delete: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardContent(
    users: List<User>,
    isLoading: Boolean,
    onDeleteUser: (User) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin Dashboard", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A))
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF1E3A8A))
            }
        } else if (users.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No users found", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        text = "User Management",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A8A)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(users) { user ->
                    UserCard(
                        user = user,
                        onDelete = { onDeleteUser(user) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun UserCard(user: User, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF1E3A8A),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = user.name, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = user.email, fontSize = 12.sp, color = Color.Gray)
                    Text(text = "Role: ${user.role}", fontSize = 12.sp, color = Color(0xFF1E3A8A))
                }
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete User",
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminDashboardPreview() {
    MatatuGuideTheme {
        AdminDashboardContent(
            users = listOf(
                User(uid = "1", name = "Reuben Langat", email = "reubenkipkirui@gmail.com", role = "admin"),
                User(uid = "2", name = "Joyline Cheprincess", email = "joyline@gmail.com", role = "user"),
                User(uid = "3", name = "Myles Kerika", email = "mylookerika@gmail.com", role = "user")
            ),
            isLoading = false,
            onDeleteUser = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    MatatuGuideTheme {
        UserCard(
            user = User(
                uid = "1",
                name = "Reuben Langat",
                email = "kipkiruireuben@gmail.com",
                role = "user"
            ),
            onDelete = {}
        )
    }
}
