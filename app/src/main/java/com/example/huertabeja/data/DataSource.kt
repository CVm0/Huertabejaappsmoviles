package com.example.huertabeja.data

import androidx.compose.runtime.mutableStateListOf
import com.example.huertabeja.R

object DataSource {
    // 1. Cambiamos a una lista mutable y observable por Compose
    val products = mutableStateListOf(
        Product(
            id = 1,
            name = "Lavanda",
            description = "Planta aromática con flores moradas, ideal para relajación.",
            price = 12.50,
            stock = 15,
            category = "Aromáticas",
            imageRes = R.drawable.lavanda
        ),
        Product(
            id = 2,
            name = "Suculenta Jade",
            description = "Planta de interior resistente, con hojas carnosas y verdes.",
            price = 8.00,
            stock = 30,
            category = "Suculentas",
            imageRes = R.drawable.suculenta_jade
        ),
        Product(
            id = 3,
            name = "Tomate Cherry",
            description = "Planta de huerto que produce pequeños y dulces tomates.",
            price = 5.75,
            stock = 25,
            category = "Huerto",
            imageRes = R.drawable.tomate_cherry
        )
    )

    // 2. Función para añadir un nuevo producto
    fun addProduct(name: String, description: String, price: Double, stock: Int) {
        val newId = (products.maxOfOrNull { it.id } ?: 0) + 1
        products.add(
            Product(
                id = newId,
                name = name,
                description = description,
                price = price,
                stock = stock,
                category = "General", // Categoría por defecto
                // Imagen por defecto. Crea una imagen genérica 'placeholder.png' en drawable
                imageRes = R.drawable.placeholder
            )
        )
    }

    // 3. Función para eliminar un producto
    fun deleteProduct(product: Product) {
        products.remove(product)
    }
}