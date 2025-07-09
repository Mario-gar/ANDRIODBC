package com.example.burningcalories.AppNavigation.kt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.burningcalories.Screen.LoginScreen
import com.example.burningcalories.Screen.MainScreen
import com.example.burningcalories.Screen.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("main") {
            MainScreen(navController)
        }
    }
}