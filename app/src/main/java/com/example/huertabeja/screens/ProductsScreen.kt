package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.huertabeja.data.DataSource
import com.example.huertabeja.data.Product
import com.example.huertabeja.viewmodel.CartViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavController, cartViewModel: CartViewModel) {
    val productList = DataSource.products
    var searchQuery by remember { mutableStateOf("") }
    val sortOptions = listOf("Nombre", "Precio", "Stock")
    var selectedSortOption by remember { mutableStateOf(sortOptions[0]) }
    var expanded by remember { mutableStateOf(false) }

    val filteredAndSortedProducts = productList
        .filter {
            it.name.contains(searchQuery, ignoreCase = true) || it.description.contains(searchQuery, ignoreCase = true)
        }
        .sortedWith(
            when (selectedSortOption) {
                "Precio" -> compareBy { it.price }
                "Stock" -> compareBy { it.stock }
                else -> compareBy { it.name }
            }
        )

    Column(
        modifier = Modifier
            .background(color = Color(0xFFFBF8F0))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar producto...") },
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sorting Dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                value = selectedSortOption,
                onValueChange = {},
                readOnly = true,
                label = { Text("Ordenar por") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                sortOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedSortOption = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = filteredAndSortedProducts, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    onAddToCart = { cartViewModel.addProduct(product) },
                    onDelete = { DataSource.deleteProduct(product) }
                )
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onAddToCart: () -> Unit, onDelete: () -> Unit) {
    val clpFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    clpFormat.maximumFractionDigits = 0

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column (modifier = Modifier.background(color = Color(0xFFDED8CA))){
            Image(
                painter = rememberAsyncImagePainter(model = product.imageUri),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Eliminar Producto",
                            tint = Color(0x81FF0000)
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
                    text = clpFormat.format(product.price),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8DA356)
                    )
                ) {
                    Text("Agregar al carrito", color = Color.White)
                }
            }
        }
    }
}
