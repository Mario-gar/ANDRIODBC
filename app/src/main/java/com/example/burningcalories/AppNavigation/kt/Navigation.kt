package com.example.calorietracker.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.burningcalories.Screen.ExerciseScreen
import com.example.burningcalories.Screen.HistoryScreen
import com.example.burningcalories.Screen.LoginScreen
import com.example.burningcalories.Screen.MainScreen
import com.example.burningcalories.Screen.RegisterScreen
import com.example.burningcalories.Screen.WaterScreen
import com.example.burningcalories.viewmodel.MainViewModel
import com.example.calorietracker.screens.*


@Composable
fun AppNavigation(navController: NavHostController) {
    // ViewModel compartido entre pantallas
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("main") {
            MainScreen(navController, viewModel)
        }

        composable("addMeal") {
            AddMealScreen(navController, viewModel)
        }

        composable("exercise") {
            ExerciseScreen(navController, viewModel)
        }
        composable("water") {
            WaterScreen(navController, viewModel)
        }

        composable("history") {
            HistoryScreen(navController, viewModel)
        }
    }
}
