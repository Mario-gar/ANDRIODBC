package com.example.burningcalories.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class FoodEntry(val name: String, val calories: Int)

@Composable
fun MainScreen(navController: NavController) {


    val foodList = remember {
        mutableStateListOf(
            FoodEntry("Desayuno: Avena con fruta", 320),
            FoodEntry("Almuerzo: Pollo y arroz", 550),
            FoodEntry("Snack: Yogurt", 100)
        )
    }

    val calorieGoal = 2000
    val caloriesConsumed = foodList.sumOf { it.calories }
    val caloriesRemaining = calorieGoal - caloriesConsumed

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF6E0), Color(0xFFFFD180))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text(
                "🔥 Burning Calories",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD84315)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "¡Hola! Aquí está tu resumen de hoy 👇",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text("Resumen de Calorías", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Consumidas:", fontWeight = FontWeight.Medium)
                        Text("$caloriesConsumed kcal", color = Color(0xFFD84315))
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Objetivo:", fontWeight = FontWeight.Medium)
                        Text("$calorieGoal kcal", color = Color(0xFF388E3C))
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Restantes:", fontWeight = FontWeight.Medium)
                        Text(
                            "$caloriesRemaining kcal",
                            color = if (caloriesRemaining >= 0) Color(0xFF1976D2) else Color.Red
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Text("Comidas registradas", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(foodList) { entry ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(entry.name, fontSize = 16.sp)
                            Text("${entry.calories} kcal", color = Color(0xFFD84315))
                        }
                    }
                }
            }


            Button(
                onClick = {

                    foodList.add(FoodEntry("Nueva comida", 300))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
            ) {
                Text("Agregar comida", color = Color.White)
            }
        }
    }
}