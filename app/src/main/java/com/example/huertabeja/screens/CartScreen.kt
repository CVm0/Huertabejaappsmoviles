package com.example.huertabeja.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.huertabeja.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val uiState by cartViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras") },
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
        },
        bottomBar = {
            if (uiState.products.isNotEmpty()) {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total: $%.2f".format(uiState.totalPrice),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Button(onClick = { /* Lógica para finalizar compra */ }) {
                            Text("Finalizar Compra")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        if (uiState.products.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("El carrito está vacío", fontSize = 20.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.products.toList()) { (product, quantity) ->
                    CartItem(
                        product = product,
                        quantity = quantity,
                        onIncrease = { cartViewModel.addProduct(product) },
                        onDecrease = { cartViewModel.decreaseQuantity(product) },
                        onRemoveFromCart = { cartViewModel.removeProductFromCart(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun CartItem(
    product: com.example.huertabeja.data.Product,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemoveFromCart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("Precio: $%.2f".format(product.price))
                Text("Subtotal: $%.2f".format(product.price * quantity))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDecrease) {
                    Icon(Icons.Filled.Remove, contentDescription = "Disminuir cantidad")
                }
                Text(text = quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                IconButton(onClick = onIncrease) {
                    Icon(Icons.Filled.Add, contentDescription = "Aumentar cantidad")
                }
                IconButton(onClick = onRemoveFromCart) {
                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar producto del carrito")
                }
            }
        }
    }
}
