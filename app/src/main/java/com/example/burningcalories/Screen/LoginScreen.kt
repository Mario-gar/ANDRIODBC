package com.example.burningcalories.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF6E0),
                        Color(0xFFFFD180)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.95f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🔥 Burning Calories",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD84315) // rojo quemado
                )

                Text(
                    text = "Bienvenido de nuevo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF37474F)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {

                        navController.navigate("main")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                ) {
                    Text("Ingresar", fontSize = 16.sp, color = Color.White)
                }

                TextButton(
                    onClick = { navController.navigate("register") },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("¿No tienes cuenta? Regístrate", color = Color(0xFF1976D2))
                }
            }
        }
    }
}