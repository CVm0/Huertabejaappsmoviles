package com.example.huertabeja.data.api

import com.example.huertabeja.data.Order
import com.example.huertabeja.data.Product
import com.example.huertabeja.data.model.LoginRequest
import com.example.huertabeja.data.model.LoginResponse
import com.example.huertabeja.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Interface completa (mantiene compatibilidad con código existente)
interface ApiService {
    
    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: String): Response<User>
}

// Interface específica para usuarios
interface UserApiService {
    
    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: String): Response<User>
}

// Interface específica para productos y pedidos
interface ProductApiService {
    @GET(".")  // El endpoint raíz devuelve todos los productos
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("{slug}")  // Obtener producto por slug en lugar de ID
    suspend fun getProductBySlug(@Path("slug") slug: String): Response<Product>
}