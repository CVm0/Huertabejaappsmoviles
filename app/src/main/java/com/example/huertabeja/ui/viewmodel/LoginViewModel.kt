package com.example.huertabeja.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertabeja.data.api.RetrofitClient
import com.example.huertabeja.data.model.LoginRequest
import com.example.huertabeja.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val user: User) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _uiState.value = LoginUiState.Loading
                
                val response = RetrofitClient.apiService.login(
                    LoginRequest(email, password)
                )
                
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.success == true && loginResponse.user != null) {
                        _uiState.value = LoginUiState.Success(loginResponse.user)
                    } else {
                        _uiState.value = LoginUiState.Error(
                            loginResponse?.message ?: "Credenciales incorrectas"
                        )
                    }
                } else {
                    val errorMessage = when (response.code()) {
                        401 -> "Credenciales incorrectas"
                        404 -> "Usuario no encontrado"
                        500 -> "Error del servidor. Intenta más tarde"
                        else -> "Error de conexión. Código: ${response.code()}"
                    }
                    _uiState.value = LoginUiState.Error(errorMessage)
                }
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(
                    "Error de red: ${e.message ?: "Verifica tu conexión a internet"}"
                )
            }
        }
    }
    
    fun resetState() {
        _uiState.value = LoginUiState.Idle
    }
}
