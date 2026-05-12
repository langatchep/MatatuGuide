package com.joyline.matatuguide.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "user", // "user" or "admin"
    val createdAt: Long = System.currentTimeMillis()
)
