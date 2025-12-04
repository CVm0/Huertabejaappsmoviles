package com.example.huertabeja.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    
    // Base URL para usuarios
    private const val USERS_BASE_URL = "https://web-production-1f2fa.up.railway.app/"
    
    // Base URL para productos y pedidos
    private const val PRODUCTS_BASE_URL = "https://servicio-productos.fly.dev/"
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    // Retrofit para usuarios
    private val retrofitUsers = Retrofit.Builder()
        .baseUrl(USERS_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    // Retrofit para productos y pedidos
    private val retrofitProducts = Retrofit.Builder()
        .baseUrl(PRODUCTS_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    // Servicio para usuarios (mantiene compatibilidad con código existente)
    val apiService: ApiService = retrofitUsers.create(ApiService::class.java)
    
    // Servicios separados
    val userService: UserApiService = retrofitUsers.create(UserApiService::class.java)
    val productService: ProductApiService = retrofitProducts.create(ProductApiService::class.java)
}
