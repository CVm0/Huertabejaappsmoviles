package com.example.huertabeja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertabeja.data.Order
import com.example.huertabeja.data.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class OrdersUiState(
    val orders: List<Order> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class OrdersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrdersUiState())
    val uiState: StateFlow<OrdersUiState> = _uiState.asStateFlow()

    private val productApiService = RetrofitClient.productService

    fun loadOrders(userId: String?) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                // TODO: La API de productos aún no tiene endpoints de órdenes
                // Por ahora mostramos una lista vacía
                _uiState.value = _uiState.value.copy(
                    orders = emptyList(),
                    isLoading = false,
                    error = "La API de órdenes aún no está implementada"
                )
                
                /* Descomentar cuando la API tenga endpoints de órdenes
                val response = if (userId != null) {
                    productApiService.getOrdersByUserId(userId)
                } else {
                    productApiService.getAllOrders()
                }

                if (response.isSuccessful) {
                    val orders = response.body() ?: emptyList()
                    _uiState.value = _uiState.value.copy(
                        orders = orders,
                        isLoading = false,
                        error = null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Error al cargar los pedidos: ${response.code()}"
                    )
                }
                */
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error de conexión: ${e.message}"
                )
            }
        }
    }

    fun refresh(userId: String?) {
        loadOrders(userId)
    }
}
