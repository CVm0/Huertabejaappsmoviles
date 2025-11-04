package com.example.huertabeja.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.huertabeja.screens.*
import com.example.huertabeja.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Cart,
        BottomNavItem.Login,
        BottomNavItem.About
    )

    val screenTitle = when (currentRoute) {
        AppScreens.HomeScreen.route -> "Inicio"
        AppScreens.ProductsScreen.route -> "Nuestros Productos"
        AppScreens.AddProductScreen.route -> "Agregar Producto"
        AppScreens.CartScreen.route -> "Carrito de Compras"
        AppScreens.AboutScreen.route -> "Sobre Nosotros"
        AppScreens.LoginScreen.route -> "Perfil"
        AppScreens.RegisterScreen.route -> "Registro"
        else -> "Huertabeja"
    }

    Scaffold(
        topBar = {
            if (currentRoute != AppScreens.HomeScreen.route) {
                TopAppBar(
                    title = { Text(screenTitle) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF8DA356),
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFF0F4E3)) {
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF8E9B6B),
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = Color(0xFF8E9B6B),
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.HomeScreen.route,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = AppScreens.HomeScreen.route) {
                HomeScreen(navController)
            }
            composable(route = AppScreens.LoginScreen.route) {
                LoginScreen(navController)
            }
            composable(route = AppScreens.RegisterScreen.route) {
                RegisterScreen(navController)
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
}