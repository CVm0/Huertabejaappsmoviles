package com.example.huertabeja.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: String? = null,
    val run: Int,
    val dv: Int,
    @SerializedName("pnombre")
    val pnombre: String,  // Primer nombre
    @SerializedName("snombre")
    val snombre: String?,  // Segundo nombre (opcional)
    @SerializedName("appaterno")
    val appaterno: String,  // Apellido paterno
    @SerializedName("apmaterno")
    val apmaterno: String,  // Apellido materno
    val email: String,
    val telefono: Int,
    val fechareg: String?,  // Fecha de registro en formato "YYYY-MM-DD"
    val password: String
)
