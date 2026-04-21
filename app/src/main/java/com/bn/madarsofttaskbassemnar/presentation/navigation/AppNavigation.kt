package com.bn.madarsofttaskbassemnar.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bn.madarsofttaskbassemnar.presentation.display.DisplayScreen
import com.bn.madarsofttaskbassemnar.presentation.input.InputScreen
import com.bn.madarsofttaskbassemnar.presentation.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Input : Screen("input")
    object Display : Screen("display")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Input.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Input.route) {
            InputScreen(onNavigateToDisplay = {
                navController.navigate(Screen.Display.route)
            })
        }

        composable(Screen.Display.route) {
            DisplayScreen(onNavigateToInput = {
                navController.navigate(Screen.Input.route)
            })
        }
    }
}
