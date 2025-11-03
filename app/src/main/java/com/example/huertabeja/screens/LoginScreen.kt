package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.huertabeja.R

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .background(color = Color(0xFFFBF8F0))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.logo_huertabeja),
            contentDescription = "Logo de Huertabeja",
            modifier = Modifier.size(300.dp),
            alignment = Alignment.BottomEnd
        )
        Spacer(modifier = Modifier.height(16.dp))
        customOutlinedTextField(
            value = username,
            onValueChange = { username = it },
            labelText = "Usuario",
            icon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(16.dp))
        customOutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null // Clear error on change
            },
            labelText = "Contraseña",
            icon = Icons.Default.Lock,
            isPassword = true,
            isError = passwordError != null
        )

        passwordError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¿Olvidaste tu contraseña?",
            color = Color(0xFF3C4522),
            modifier = Modifier.clickable {
                // Acción al hacer clic. Por ejemplo, navegar a una pantalla de recuperación.
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                val specialCharRegex = Regex("[^A-Za-z0-9]")
                if (password.length < 8 || password.length > 12) {
                    passwordError = "La contraseña debe tener entre 8 y 12 caracteres."
                } else if (!specialCharRegex.containsMatchIn(password)) {
                    passwordError = "La contraseña debe contener al menos un carácter especial."
                } else {
                    passwordError = null
                    navController.popBackStack()
                }
            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8E9B6B) // Verde claro
            )
        ) {
            Text("Iniciar sesión", color = Color.Black)
        }
        Spacer(modifier = Modifier.weight(2.5f))
    }
}

@Composable
fun customOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    isError: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        leadingIcon = {
            Icon(
                imageVector = icon, // <-- 2. Usar el parámetro recibido
                contentDescription = "$labelText icon" // Descripción de contenido dinámica
            )
        },
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible)
                    R.drawable.hide
                else
                    R.drawable.visible

                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = painterResource(id = image), contentDescription = description)
                }
            }
        },
        isError = isError
    )
}
