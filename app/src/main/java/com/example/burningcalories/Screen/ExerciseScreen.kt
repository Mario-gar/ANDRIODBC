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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burningcalories.viewmodel.ExerciseEntry
import com.example.burningcalories.viewmodel.MainViewModel
@Composable
fun ExerciseScreen(navController: NavController, viewModel: MainViewModel) {
    var exerciseName by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var caloriesBurned by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFE3F2FD), Color(0xFF90CAF9))
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
                Text("🏋️ Registrar ejercicio", style = MaterialTheme.typography.headlineSmall)

                OutlinedTextField(
                    value = exerciseName,
                    onValueChange = { exerciseName = it },
                    label = { Text("Nombre del ejercicio") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duración (minutos)") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = caloriesBurned,
                    onValueChange = { caloriesBurned = it },
                    label = { Text("Calorías quemadas") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (exerciseName.isNotBlank() && caloriesBurned.isNotBlank()) {
                            viewModel.addExercise(
                                ExerciseEntry(
                                    name = exerciseName,
                                    duration = duration.toIntOrNull() ?: 0,
                                    calories = caloriesBurned.toIntOrNull() ?: 0
                                )
                            )
                            navController.popBackStack() // volver a MainScreen
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Guardar ejercicio", color = Color.White)
                }
            }
        }
    }
}

