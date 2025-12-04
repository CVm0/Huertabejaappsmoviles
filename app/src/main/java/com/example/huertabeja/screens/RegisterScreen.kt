package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.huertabeja.R

@Composable
fun RegisterScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    
    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .background(color = Color(0xFFFBF8F0))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        Image(
            painter = painterResource(id = R.drawable.logo_huertabeja),
            contentDescription = "Logo de Huertabeja",
            modifier = Modifier.size(200.dp),
            alignment = Alignment.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Crear Cuenta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3C4522)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Campo Nombre Completo
        RegisterOutlinedTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                fullNameError = null
            },
            labelText = "Nombre Completo",
            icon = Icons.Default.Person,
            isError = fullNameError != null
        )
        
        fullNameError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Campo Email
        RegisterOutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            labelText = "Correo Electrónico",
            icon = Icons.Default.Email,
            keyboardType = KeyboardType.Email,
            isError = emailError != null
        )
        
        emailError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Campo Teléfono
        RegisterOutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = null
            },
            labelText = "Teléfono",
            icon = Icons.Default.Phone,
            keyboardType = KeyboardType.Phone,
            isError = phoneError != null
        )
        
        phoneError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Campo Contraseña
        RegisterOutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
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
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Campo Confirmar Contraseña
        RegisterOutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = null
            },
            labelText = "Confirmar Contraseña",
            icon = Icons.Default.Lock,
            isPassword = true,
            isError = confirmPasswordError != null
        )
        
        confirmPasswordError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Botón de Registro
        Button(
            onClick = {
                var hasError = false
                
                // Validar nombre completo
                if (fullName.isBlank()) {
                    fullNameError = "El nombre es obligatorio."
                    hasError = true
                } else if (fullName.length < 3) {
                    fullNameError = "El nombre debe tener al menos 3 caracteres."
                    hasError = true
                }
                
                // Validar email
                val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
                if (email.isBlank()) {
                    emailError = "El correo electrónico es obligatorio."
                    hasError = true
                } else if (!emailRegex.matches(email)) {
                    emailError = "Ingresa un correo electrónico válido."
                    hasError = true
                }
                
                // Validar teléfono
                val phoneRegex = Regex("^[0-9]{9,12}\$")
                if (phone.isBlank()) {
                    phoneError = "El teléfono es obligatorio."
                    hasError = true
                } else if (!phoneRegex.matches(phone)) {
                    phoneError = "Ingresa un número de teléfono válido (9-12 dígitos)."
                    hasError = true
                }
                
                // Validar contraseña
                val specialCharRegex = Regex("[^A-Za-z0-9]")
                if (password.isBlank()) {
                    passwordError = "La contraseña es obligatoria."
                    hasError = true
                } else if (password.length < 8 || password.length > 12) {
                    passwordError = "La contraseña debe tener entre 8 y 12 caracteres."
                    hasError = true
                } else if (!specialCharRegex.containsMatchIn(password)) {
                    passwordError = "La contraseña debe contener al menos un carácter especial."
                    hasError = true
                }
                
                // Validar confirmación de contraseña
                if (confirmPassword.isBlank()) {
                    confirmPasswordError = "Debes confirmar tu contraseña."
                    hasError = true
                } else if (password != confirmPassword) {
                    confirmPasswordError = "Las contraseñas no coinciden."
                    hasError = true
                }
                
                // Si no hay errores, navegar de vuelta
                if (!hasError) {
                    // Aquí podrías guardar el usuario en una base de datos o SharedPreferences
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8E9B6B)
            )
        ) {
            Text("Registrarse", color = Color.Black, fontSize = 16.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Link para ir a Login
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Ya tienes cuenta? ",
                color = Color(0xFF3C4522)
            )
            Text(
                text = "Inicia sesión",
                color = Color(0xFF8E9B6B),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun RegisterOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "\$labelText icon"
            )
        },
        visualTransformation = if (isPassword && !passwordVisible) 
            PasswordVisualTransformation() 
        else 
            VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else keyboardType
        ),
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        },
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )
}
