package com.example.huertabeja.data.model

data class UserRegisterRequest(
    val run: Int,
    val dv: Int,  // DV como Int: 0-9 (K se convierte a 10)
    val nombres: String,
    val apellidos: String,
    val email: String,
    val telefono: Int,
    val password: String
)
