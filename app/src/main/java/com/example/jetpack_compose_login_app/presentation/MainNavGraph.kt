package com.example.jetpack_compose_login_app.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_login_app.presentation.home.HomeScreen
import com.example.jetpack_compose_login_app.presentation.login.LoginScreen
import com.example.jetpack_compose_login_app.presentation.main.MainScreen
import com.example.jetpack_compose_login_app.presentation.profile.ProfileScreen
import com.example.jetpack_compose_login_app.presentation.settings.SettingsScreen
import com.example.jetpack_compose_login_app.presentation.signup.SignUpScreen

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navToSignUp = { navController.navigate("signup") },
                navToMain = { navController.navigate("home") })
        }
        composable("home") { HomeScreen() }
        composable("settings") { SettingsScreen() }
        composable("profile") { ProfileScreen() }
        composable("home") { MainScreen() }
        composable("signUp") {
            SignUpScreen(navToLogin = { navController.navigate("login") },
                popBackStack = { navController.popBackStack() })

        }
    }
}