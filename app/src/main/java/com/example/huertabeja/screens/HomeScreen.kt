package com.example.huertabeja.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.huertabeja.navigation.AppScreens
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a Huertabeja",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Botón para ir a la pantalla de Login
        Button(onClick = { navController.navigate(AppScreens.LoginScreen.route) }) {
            Text("Iniciar Sesión (Login)")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Botón para ir a la lista de Productos
        Button(onClick = { navController.navigate(AppScreens.ProductsScreen.route) }) {
            Text("Ver Productos")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Botón para ir al formulario de Agregar Producto
        Button(onClick = { navController.navigate(AppScreens.AddProductScreen.route) }) {
            Text("Agregar Producto")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Botón para ir al Carrito de compras
        Button(onClick = { navController.navigate(AppScreens.CartScreen.route) }) {
            Text("Ver Carrito")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Botón para ir a la pantalla "Sobre Nosotros"
        Button(onClick = { navController.navigate(AppScreens.AboutScreen.route) }) {
            Text("Sobre Nosotros")
        }
    }
}