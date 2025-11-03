package com.example.huertabeja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.huertabeja.R // Asegúrate de importar tu clase R
import com.example.huertabeja.ui.theme.HuertabejaTheme

@Composable
fun AboutScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFBF8F0))
            .padding(16.dp)
    ) {

        // -- Sección Misión --
        item {
            SectionTitle(title = "Nuestra Misión")
            Text(
                text = "Aquí va el texto que describe la misión de Huertabeja. Queremos conectar a las personas con la naturaleza a través de la apicultura y la horticultura sostenible, promoviendo la biodiversidad y el respeto por el medio ambiente.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // -- Sección Historia --
        item {
            SectionTitle(title = "Nuestra Historia")
            Text(
                text = "Aquí se narra la historia de cómo comenzó el proyecto Huertabeja. Desde la idea inicial hasta los hitos más importantes que hemos alcanzado como comunidad.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // -- Sección Equipo --
        item {
            SectionTitle(title = "Nuestro Equipo")
        }
        item {
            // Suponiendo que tienes una lista de miembros del equipo
            val teamMembers = listOf(
                "Ana García", "Carlos Pérez", "Sofía López"
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(teamMembers) { memberName ->
                    TeamMemberAvatar(name = memberName)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // -- Sección Contacto --
        item {
            SectionTitle(title = "Contacto")
            ContactInfo(icon = Icons.Default.Email, text = "contacto@huertabeja.com")
            ContactInfo(icon = Icons.Default.Phone, text = "+34 123 456 789")
            // Aquí podrías añadir iconos de redes sociales
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun TeamMemberAvatar(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Reemplaza con una imagen genérica o específica
            contentDescription = "Avatar de $name",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ContactInfo(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    HuertabejaTheme {
        val navController = rememberNavController()
        AboutScreen(navController = navController)
    }
}
