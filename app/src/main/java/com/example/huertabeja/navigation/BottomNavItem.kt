package com.example.huertabeja.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Home : BottomNavItem(
        title = "Inicio",
        icon = Icons.Default.Home,
        route = AppScreens.HomeScreen.route
    )
    object Cart : BottomNavItem(
        title = "Carrito",
        icon = Icons.Default.ShoppingCart,
        route = AppScreens.CartScreen.route
    )
    object Orders : BottomNavItem(
        title = "Pedidos",
        icon = Icons.Default.ShoppingBag,
        route = AppScreens.OrdersScreen.route
    )
    object Login : BottomNavItem(
        title = "Perfil",
        icon = Icons.Default.Person,
        route = AppScreens.PerfilUserScreen.route
    )
    object About : BottomNavItem(
        title = "Nosotros",
        icon = Icons.Default.Info,
        route = AppScreens.AboutScreen.route
    )
}
