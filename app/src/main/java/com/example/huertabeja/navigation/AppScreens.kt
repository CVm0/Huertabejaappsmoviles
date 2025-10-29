package com.example.huertabeja.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen : AppScreens("home_screen")
    object LoginScreen : AppScreens("login_screen")
    object ProductsScreen : AppScreens("products_screen")
    object AddProductScreen : AppScreens("add_product_screen")
    object CartScreen : AppScreens("cart_screen")
    object AboutScreen : AppScreens("about_screen")
}