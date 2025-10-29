package com.example.huertabeja.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.huertabeja.screens.*
import com.example.huertabeja.viewmodel.CartViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.route // Empezamos en la pantalla de inicio
    ) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.ProductsScreen.route) {
            ProductsScreen(navController, cartViewModel)
        }
        composable(route = AppScreens.AddProductScreen.route) {
            AddProductScreen(navController)
        }
        composable(route = AppScreens.CartScreen.route) {
            CartScreen(navController, cartViewModel)
        }
        composable(route = AppScreens.AboutScreen.route) {
            AboutScreen(navController)
        }
    }
}