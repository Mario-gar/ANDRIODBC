package com.example.calorietracker.screens

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burningcalories.viewmodel.FoodEntry
import com.example.burningcalories.viewmodel.MainViewModel

@Composable
fun AddMealScreen(navController: NavController, viewModel: MainViewModel) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var protein by remember { mutableStateOf("") }
    var carbs by remember { mutableStateOf("") }
    var fat by remember { mutableStateOf("") }
    var mealTime by remember { mutableStateOf("Desayuno") }

    val mealOptions = listOf("Desayuno", "Almuerzo", "Cena", "Snack")

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
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("🍽️ Registrar comida", style = MaterialTheme.typography.headlineSmall)

                OutlinedTextField(
                    value = mealName,
                    onValueChange = { mealName = it },
                    label = { Text("Nombre del alimento") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calorías") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = protein,
                    onValueChange = { protein = it },
                    label = { Text("Proteínas (g)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = carbs,
                    onValueChange = { carbs = it },
                    label = { Text("Carbohidratos (g)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = fat,
                    onValueChange = { fat = it },
                    label = { Text("Grasas (g)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownSelector("Momento del día", mealOptions, mealTime) {
                    mealTime = it
                }

                Button(
                    onClick = {
                        if (mealName.isNotBlank() && calories.isNotBlank()) {
                            viewModel.addFood(
                                FoodEntry(
                                    name = mealName,
                                    calories = calories.toIntOrNull() ?: 0,
                                    protein = protein.toIntOrNull() ?: 0,
                                    carbs = carbs.toIntOrNull() ?: 0,
                                    fat = fat.toIntOrNull() ?: 0,
                                    mealTime = mealTime
                                )
                            )
                            navController.popBackStack() // Regresar al MainScreen
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                ) {
                    Text("Guardar comida", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun DropdownSelector(label: String, options: List<String>, selected: String, onSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF2C3E50))
            ) {
                Text(selected)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            onSelected(it)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
