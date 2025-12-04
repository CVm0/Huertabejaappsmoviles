package com.example.huertabeja.data

import java.util.Date

data class Order(
    val id: String,
    val date: Date,
    val items: List<OrderItem>,
    val total: Double,
    val status: OrderStatus
)

data class OrderItem(
    val product: Product,
    val quantity: Int,
    val price: Double
)

enum class OrderStatus {
    PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}

fun OrderStatus.toDisplayString(): String {
    return when(this) {
        OrderStatus.PENDING -> "Pendiente"
        OrderStatus.PROCESSING -> "En Proceso"
        OrderStatus.SHIPPED -> "Enviado"
        OrderStatus.DELIVERED -> "Entregado"
        OrderStatus.CANCELLED -> "Cancelado"
    }
}
