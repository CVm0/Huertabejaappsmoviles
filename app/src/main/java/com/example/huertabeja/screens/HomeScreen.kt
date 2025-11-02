package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.huertabeja.R
import com.example.huertabeja.data.Product
import com.example.huertabeja.navigation.AppScreens
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {
    val products = listOf(
        Product(1, "Miel de Abeja", "Miel pura y natural", 25000.0, 10, "Miel", ""),
        Product(2, "Polen de Abeja", "Polen fresco y nutritivo", 30000.0, 15, "Polen", ""),
        Product(3, "Jalea Real", "Jalea real fresca", 50000.0, 5, "Jalea Real", "")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Productos Destacados",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product, onClick = {
                    navController.navigate(AppScreens.ProductsScreen.route)
                })
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Botones de navegación
        Button(onClick = { navController.navigate(AppScreens.ProductsScreen.route) }) {
            Text("Ver Todos los Productos")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(AppScreens.AddProductScreen.route) }) {
            Text("Agregar Producto")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(AppScreens.CartScreen.route) }) {
            Text("Ver Carrito")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(AppScreens.LoginScreen.route) }) {
            Text("Perfil / Iniciar Sesión")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(AppScreens.AboutScreen.route) }) {
            Text("Sobre Nosotros")
        }
    }
}

@Composable
private fun ProductCard(product: Product, onClick: () -> Unit) {
    val clpFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    clpFormat.maximumFractionDigits = 0

    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Imagen de ${product.name}",
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = clpFormat.format(product.price),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
