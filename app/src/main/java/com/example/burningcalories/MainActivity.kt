package com.example.burningcalories


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.burningcalories.AppNavigation.kt.AppNavigation
import com.example.burningcalories.ui.theme.BURNINGCALORIESTheme

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

