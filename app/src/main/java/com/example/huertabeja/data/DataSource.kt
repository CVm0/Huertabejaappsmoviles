package com.example.huertabeja.data

import androidx.compose.runtime.mutableStateListOf
import com.example.huertabeja.R

object DataSource {
    val products = mutableStateListOf(
        Product(
            id = 1,
            name = "Lavanda",
            description = "Planta aromática con flores moradas, ideal para relajación.",
            price = 12.000,
            stock = 15,
            category = "Aromáticas",
            imageUri = "android.resource://com.example.huertabeja/" + R.drawable.lavanda
        ),
        Product(
            id = 2,
            name = "Suculenta Jade",
            description = "Planta de interior resistente, con hojas carnosas y verdes.",
            price = 80.000,
            stock = 30,
            category = "Suculentas",
            imageUri = "android.resource://com.example.huertabeja/" + R.drawable.suculenta_jade
        ),
        Product(
            id = 3,
            name = "Tomate Cherry",
            description = "Planta de huerto que produce pequeños y dulces tomates.",
            price = 5.750,
            stock = 25,
            category = "Huerto",
            imageUri = "android.resource://com.example.huertabeja/" + R.drawable.tomate_cherry
        )
    )

    fun addProduct(name: String, description: String, price: Double, stock: Int, category: String, imageUri: String) {
        val newId = (products.maxOfOrNull { it.id } ?: 0) + 1
        products.add(
            Product(
                id = newId,
                name = name,
                description = description,
                price = price,
                stock = stock,
                category = category,
                imageUri = imageUri
            )
        )
    }

    fun deleteProduct(product: Product) {
        products.remove(product)
    }
}