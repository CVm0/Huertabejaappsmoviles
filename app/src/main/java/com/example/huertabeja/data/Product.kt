package com.example.huertabeja.data

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
    val category: String,
    @DrawableRes val imageRes: Int
)