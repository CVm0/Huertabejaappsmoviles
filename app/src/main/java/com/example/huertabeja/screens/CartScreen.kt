package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.huertabeja.data.Product
import com.example.huertabeja.navigation.AppNavigation
import com.example.huertabeja.navigation.AppScreens
import com.example.huertabeja.viewmodel.CartViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val uiState by cartViewModel.uiState.collectAsState()
    val clpFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    clpFormat.maximumFractionDigits = 0

    Scaffold(
        bottomBar = {
            if (uiState.products.isNotEmpty()) {
                BottomAppBar(
                    containerColor = Color(0xFFD6DCC2)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Subtotal: ${clpFormat.format(uiState.totalPrice)}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF424F37)
                        )
                        Button(
                            onClick = { navController.navigate(AppScreens.PaymentScreen.route) },
                            colors = ButtonDefaults.buttonColors(Color(0xFF4D5D41))
                        ) {
                            Text("Finalizar Compra", color = Color.White)
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        if (uiState.products.isEmpty()) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFFBF8F0))
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
    product: Product,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemoveFromCart: () -> Unit
) {
    val clpFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    clpFormat.maximumFractionDigits = 0

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = product.image),
                contentDescription = product.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(product.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Subtotal: ${clpFormat.format(product.price * quantity)}", fontSize = 16.sp)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDecrease) {
                    Icon(Icons.Filled.Remove, contentDescription = "Disminuir cantidad")
                }
                Text(text = quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp), fontSize = 18.sp)
                IconButton(onClick = onIncrease) {
                    Icon(Icons.Filled.Add, contentDescription = "Aumentar cantidad")
                }
                IconButton(onClick = onRemoveFromCart) {
                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar producto", tint = Color(0x81FF0000))
                }
            }
        }
    }
}
