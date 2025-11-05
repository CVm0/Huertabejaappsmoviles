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

        Text(
            text = registrationDate,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(AppScreens.AddProductScreen.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text("Añadir Producto", color = Color.White)
        }

        Spacer(modifier = Modifier.height(18.dp))
        // Botón de Cerrar Sesión
        Button(
            onClick = {
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
