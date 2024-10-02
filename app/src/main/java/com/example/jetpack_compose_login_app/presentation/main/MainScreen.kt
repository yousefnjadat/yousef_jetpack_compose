package com.example.jetpack_compose_login_app.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpack_compose_login_app.presentation.BottomNavigationBar
import com.example.jetpack_compose_login_app.presentation.home.HomeScreen
import com.example.jetpack_compose_login_app.presentation.profile.ProfileScreen
import com.example.jetpack_compose_login_app.presentation.settings.SettingsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("settings") { SettingsScreen() }
        composable("profile") { ProfileScreen() }
    }
}