package com.example.huertabeja.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertabeja.data.api.RetrofitClient
import com.example.huertabeja.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PerfilUiState {
    object Idle : PerfilUiState()
    object Loading : PerfilUiState()
    data class Success(val user: User) : PerfilUiState()
    data class Error(val message: String) : PerfilUiState()
}

class PerfilViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow<PerfilUiState>(PerfilUiState.Idle)
    val uiState: StateFlow<PerfilUiState> = _uiState
    
    fun loadUserProfile(userId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = PerfilUiState.Loading
                
                val response = RetrofitClient.apiService.getUserById(userId)
                
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        _uiState.value = PerfilUiState.Success(user)
                    } else {
                        _uiState.value = PerfilUiState.Error("No se pudo cargar el perfil del usuario")
                    }
                } else {
                    val errorMessage = when (response.code()) {
                        404 -> "Usuario no encontrado"
                        500 -> "Error del servidor. Intenta más tarde"
                        else -> "Error al cargar perfil. Código: ${response.code()}"
                    }
                    _uiState.value = PerfilUiState.Error(errorMessage)
                }
            } catch (e: Exception) {
                _uiState.value = PerfilUiState.Error(
                    "Error de red: ${e.message ?: "Verifica tu conexión a internet"}"
                )
            }
        }
    }
    
    fun resetState() {
        _uiState.value = PerfilUiState.Idle
    }
}
