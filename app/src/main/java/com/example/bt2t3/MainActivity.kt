package com.example.bt2t3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bt2t3.data.Task
import com.example.bt2t3.ui.screens.DetailScreen
import com.example.bt2t3.ui.screens.HomeScreen
import com.example.bt2t3.ui.screens.OnboardingScreen
import com.example.bt2t3.ui.screens.SecondScreen
import com.example.bt2t3.ui.screens.ThirdScreen
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { OnboardingScreen(navController) }
                composable("SecondScreen") { SecondScreen(navController) }
                composable("ThirdScreen") { ThirdScreen(navController) }
                composable("HomeScreen") { HomeScreen(navController) }
                composable("DetailScreen/{taskJson}") { backStackEntry ->
                    val taskJson = backStackEntry.arguments?.getString("taskJson") ?: ""
                    DetailScreen(navController, taskJson )
                }
            }
        }
    }
}
