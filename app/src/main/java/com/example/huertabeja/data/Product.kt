package com.example.huertabeja.data

data class Product(
    val title: String,
    val price: Int,
    val price_offer: Int = 0,
    val image: String,
    val description: String,
    val rating: Rating? = null,
    val stock: Int,
    val category: String,
    val home: Boolean = false,
    val slug: String
)
data class Rating(
    val rate: Double,
    val count: Int
)