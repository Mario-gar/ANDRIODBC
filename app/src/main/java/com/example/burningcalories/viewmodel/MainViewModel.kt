package com.example.burningcalories.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// ------------ Datos -----------------
data class FoodEntry(
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val mealTime: String
)

data class ExerciseEntry(
    val name: String,
    val duration: Int,
    val calories: Int
)

data class WaterEntry(
    val amount: Int // en mililitros
)

// ------------ ViewModel -------------
class MainViewModel : ViewModel() {

    val foodList = mutableStateListOf<FoodEntry>()
    val exerciseList = mutableStateListOf<ExerciseEntry>()
    val waterList = mutableStateListOf<WaterEntry>() // ✅ AGREGA ESTA LÍNEA

    fun addFood(entry: FoodEntry) {
        foodList.add(entry)
    }

    fun addExercise(entry: ExerciseEntry) {
        exerciseList.add(entry)
    }

    fun addWater(entry: WaterEntry) {
        waterList.add(entry)
    }
}
