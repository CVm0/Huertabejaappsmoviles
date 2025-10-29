package com.example.huertabeja.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.huertabeja.data.DataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController) {
    // Estados para guardar los valores de los campos de texto
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    val context = LocalContext.current // Contexto para mostrar mensajes (Toast)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Nuevo Producto") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo de texto para el nombre
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre del Producto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para la descripción
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para el precio
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal) 
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para el stock
            OutlinedTextField(
                value = stock,
                onValueChange = { stock = it },
                label = { Text("Stock Inicial") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Botón para guardar el producto
            Button(
                onClick = {
                    // Validaciones básicas
                    val priceDouble = price.toDoubleOrNull()
                    val stockInt = stock.toIntOrNull()

                    if (name.isNotBlank() && description.isNotBlank() && priceDouble != null && stockInt != null) {
                        // Llamamos a la función para añadir el producto
                        DataSource.addProduct(name, description, priceDouble, stockInt)
                        // Mostramos mensaje de éxito
                        Toast.makeText(context, "Producto agregado con éxito", Toast.LENGTH_SHORT).show()
                        // Volvemos a la pantalla anterior
                        navController.popBackStack()
                    } else {
                        // Mostramos mensaje de error
                        Toast.makeText(context, "Por favor, rellena todos los campos correctamente", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Producto")
            }
        }
    }
}