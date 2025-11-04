package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.huertabeja.R
import com.example.huertabeja.navigation.AppScreens

@Composable
fun PerfilScreen(navController: NavController) {
    // Datos de ejemplo para el perfil de usuario
    val userName = "Carlos"
    val userEmail = "carlos@example.com"
    val registrationDate = "Miembro desde: 01/01/2024"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFBF8F0))
            .padding(16.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(color = Color(0xFFDED8CA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.emoji), // Usando el logo como placeholder
            contentDescription = "Foto de perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nombre de usuario
        Text(
            text = userName,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Correo electrónico del usuario
        Text(
            text = userEmail,
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fecha de registro
        Text(
            text = registrationDate,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f)) // Empuja el botón de cierre de sesión hacia abajo

        // Botón de Cerrar Sesión
        Button(
            onClick = {
                // Lógica de cierre de sesión
                // Navega a la pantalla de inicio y limpia la pila de navegación
                navController.navigate(AppScreens.LoginScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text("Cerrar Sesión", color = Color.White)
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}
