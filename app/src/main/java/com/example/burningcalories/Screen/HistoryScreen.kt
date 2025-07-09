package com.example.burningcalories.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burningcalories.viewmodel.MainViewModel

@Composable
fun HistoryScreen(navController: NavController, viewModel: MainViewModel) {
    val foodList = viewModel.foodList
    val exerciseList = viewModel.exerciseList

    val totalCaloriesConsumed = foodList.sumOf { it.calories }
    val totalProtein = foodList.sumOf { it.protein }
    val totalCarbs = foodList.sumOf { it.carbs }
    val totalFat = foodList.sumOf { it.fat }

    val totalCaloriesBurned = exerciseList.sumOf { it.calories }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF3E5F5), Color(0xFFCE93D8))
                )
            )
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "📅 Historial de hoy",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "🍽️ Resumen nutricional",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text("Calorías consumidas: $totalCaloriesConsumed kcal")
                Text("Proteínas: $totalProtein g")
                Text("Carbohidratos: $totalCarbs g")
                Text("Grasas: $totalFat g")

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "🏋️ Resumen de ejercicios",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text("Calorías quemadas: $totalCaloriesBurned kcal")

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "🍽️ Comidas registradas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            items(foodList) { food ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("🍴 ${food.name}", fontWeight = FontWeight.SemiBold)
                        Text("Calorías: ${food.calories} kcal")
                        Text("Proteína: ${food.protein} g | Carbs: ${food.carbs} g | Grasas: ${food.fat} g")
                        Text("Momento: ${food.mealTime}")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "🏃 Ejercicios realizados",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            items(exerciseList) { ex ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("💪 ${ex.name}", fontWeight = FontWeight.SemiBold)
                        Text("Duración: ${ex.duration} min")
                        Text("Calorías quemadas: ${ex.calories} kcal")
                    }
                }
            }
        }
    }
}
