package com.example.huertabeja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertabeja.data.api.RetrofitClient
import com.example.huertabeja.data.model.UserRegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val message: String) : RegisterState()
    data class Error(val message: String) : RegisterState()
}

class RegisterViewModel : ViewModel() {
    
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()
    
    fun registerUser(
        run: String,
        dv: String,
        nombres: String,
        apellidos: String,
        email: String,
        telefono: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                _registerState.value = RegisterState.Loading
                
                // Convertir DV a Int: K -> 10, dígitos -> Int
                val dvInt = when (dv.uppercase()) {
                    "K" -> 10
                    else -> dv.toIntOrNull() ?: 0
                }
                
                val request = UserRegisterRequest(
                    run = run.toInt(),
                    dv = dvInt,
                    nombres = nombres,
                    apellidos = apellidos,
                    email = email,
                    telefono = telefono.toInt(),
                    password = password
                )
                
                val response = RetrofitClient.userService.registerUser(request)
                
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success("Usuario registrado exitosamente")
                } else {
                    val errorBody = response.errorBody()?.string()
                    _registerState.value = RegisterState.Error(
                        errorBody ?: "Error al registrar usuario: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(
                    e.message ?: "Error desconocido al registrar usuario"
                )
            }
        }
    }
    
    fun resetState() {
        _registerState.value = RegisterState.Idle
    }
}
