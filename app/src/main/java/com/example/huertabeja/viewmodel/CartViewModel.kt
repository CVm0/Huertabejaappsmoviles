package com.example.huertabeja.viewmodel

import androidx.lifecycle.ViewModel
import com.example.huertabeja.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CartUiState(
    val products: Map<Product, Int> = emptyMap(),
    val totalPrice: Double = 0.0
)
class CartViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    fun addProduct(product: Product) {
        _uiState.update { currentState ->
            val newProducts = currentState.products.toMutableMap()
            newProducts[product] = (newProducts[product] ?: 0) + 1

            currentState.copy(
                products = newProducts,
                totalPrice = calculateTotalPrice(newProducts)
            )
        }
    }

    fun decreaseQuantity(product: Product) {
        _uiState.update { currentState ->
            val newProducts = currentState.products.toMutableMap()
            val currentQuantity = newProducts[product] ?: 0
            if (currentQuantity > 1) {
                newProducts[product] = currentQuantity - 1
            } else {
                newProducts.remove(product)
            }

            currentState.copy(
                products = newProducts,
                totalPrice = calculateTotalPrice(newProducts)
            )
        }
    }

    fun removeProductFromCart(product: Product) {
        _uiState.update { currentState ->
            val newProducts = currentState.products.toMutableMap()
            newProducts.remove(product)

            currentState.copy(
                products = newProducts,
                totalPrice = calculateTotalPrice(newProducts)
            )
        }
    }

    fun clearCart() {
        _uiState.value = CartUiState()
    }

    private fun calculateTotalPrice(products: Map<Product, Int>): Double {
        var totalPrice = 0.0
        for ((product, quantity) in products) {
            totalPrice += product.price * quantity
        }
        return totalPrice
    }
}