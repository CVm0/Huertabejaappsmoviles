package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete // ¡IMPORTANTE! Importar icono de eliminar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.huertabeja.data.DataSource
import com.example.huertabeja.data.Product
import com.example.huertabeja.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavController, cartViewModel: CartViewModel) {
    // La lista ahora se obtiene directamente de DataSource y se actualizará sola
    val productList = DataSource.products

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuestros Productos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Usamos la clave `key` para mejorar el rendimiento de LazyColumn al eliminar elementos
            items(items = productList, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    onAddToCart = { cartViewModel.addProduct(product) },
                    // Pasamos la función de eliminar al ProductCard
                    onDelete = { DataSource.deleteProduct(product) }
                )
            }
        }
    }
}

// CAMBIO: Añadimos un nuevo parámetro `onDelete` a ProductCard
@Composable
fun ProductCard(product: Product, onAddToCart: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                // CAMBIO: Fila para el título y el botón de eliminar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f) // Permite que el texto ocupe el espacio disponible
                    )
                    // Botón para eliminar
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar Producto",
                            tint = MaterialTheme.colorScheme.error // Color rojo para indicar peligro
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${"%.2f".format(product.price)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Agregar al carrito")
                }
            }
        }
    }
}