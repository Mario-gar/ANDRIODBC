package com.example.burningcalories.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.burningcalories.model.RegisterRequest
import com.example.burningcalories.model.TipoPersona
import com.example.burningcalories.viewmodel.RegisterState
import com.example.burningcalories.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {
    // Text field states
    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var primerNombre by remember { mutableStateOf("") }
    var segundoNombre by remember { mutableStateOf("") }
    var primerApellido by remember { mutableStateOf("") }
    var segundoApellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var tipoPersona by remember { mutableStateOf(TipoPersona.ADULTO) }

    val registerState by viewModel.registerState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFF3E0), Color(0xFFFFB74D))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        if (registerState is RegisterState.Loading) {
            CircularProgressIndicator()
        } else {
            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        "🔥 Burning Calories",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD84315)
                    )
                    Text(
                        "Crea tu cuenta fitness",
                        fontSize = 16.sp,
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
                        value = contrasena,
                        onValueChange = { contrasena = it },
                        label = { Text("Contraseña") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = primerNombre,
                        onValueChange = { primerNombre = it },
                        label = { Text("Primer nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = segundoNombre,
                        onValueChange = { segundoNombre = it },
                        label = { Text("Segundo nombre (opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = primerApellido,
                        onValueChange = { primerApellido = it },
                        label = { Text("Primer apellido") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = segundoApellido,
                        onValueChange = { segundoApellido = it },
                        label = { Text("Segundo apellido (opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = edad,
                        onValueChange = { edad = it },
                        label = { Text("Edad") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Selector para TipoPersona
                    TipoPersonaDropdown(tipoPersona) {
                        tipoPersona = it
                    }

                    Button(
                        onClick = {
                            val request = RegisterRequest(
                                email = email,
                                contrasena = contrasena,
                                primerNombre = primerNombre,
                                segundoNombre = segundoNombre,
                                primerApellido = primerApellido,
                                segundoApellido = segundoApellido,
                                edad = edad.toIntOrNull() ?: 0,
                                tipoPersona = tipoPersona
                            )
                            viewModel.registerUser(request)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                    ) {
                        Text("Registrar", color = Color.White)
                    }

                    if (registerState is RegisterState.Error) {
                        Text(
                            text = (registerState as RegisterState.Error).message,
                            color = Color.Red,
                            fontSize = 14.sp
                        )
                    }

                    if (registerState is RegisterState.Success) {
                        LaunchedEffect(Unit) {
                            navController.navigate("main") {
                                popUpTo("register") { inclusive = true }
                            }
                        }
                    }

                    TextButton(
                        onClick = { navController.navigate("login") },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("¿Ya tienes cuenta? Inicia sesión", color = Color(0xFF1976D2))
                    }
                }
            }
        }
    }
}

@Composable
fun TipoPersonaDropdown(
    selected: TipoPersona,
    onSelected: (TipoPersona) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Tipo de persona", style = MaterialTheme.typography.labelMedium)

        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selected.name)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                TipoPersona.values().forEach { tipo ->
                    DropdownMenuItem(
                        text = { Text(tipo.name) },
                        onClick = {
                            onSelected(tipo)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
