package com.example.huertabeja.data.model

data class LoginResponse(
    val success: Boolean,
    val message: String? = null,
    val user: User? = null
)
