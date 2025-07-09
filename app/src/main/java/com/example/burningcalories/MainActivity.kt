package com.example.burningcalories


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.burningcalories.ui.theme.BURNINGCALORIESTheme
import com.example.calorietracker.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BURNINGCALORIESTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

