package com.example.jetpack_compose_login_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_login_app.database.UserModelViewModel
import com.example.jetpack_compose_signup_app.SignUpScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.jetpack_compose_login_app.database.UserModelDatabase

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

@Composable
fun MyApp(vm: UserModelViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController,vm) }
        composable("home") { HomeScreen() }
        composable("settings") { SettingsScreen() }
        composable("profile") { ProfileScreen() }
        composable("main") { MainScreen() }
        composable("signUp") { SignUpScreen(navController,vm) }
    }
}

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,  // Make sure context is properly referenced
            UserModelDatabase::class.java,  // Class of the database
            "usermodel.db"  // Database name
        ).build()  // Use .build() to create the instance
    }

    private val viewModel by viewModels<UserModelViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserModelViewModel(db.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            LoginScreen()
            MyApp(vm = viewModel)
        }
    }
}

//@Composable
//fun DatabaseListView(vm: UserModelViewModel) {
//    val dataList = vm.getAllRecords().collectAsState(initial = emptyList())
//
//    Column {
//        Button(onClick = {
//            vm.addData(
//                "yousef odeh najadat",
//                "yousef.najadat98@gmail.com",
//                "1234"
//            )
//        }) {
//            Text("Add Items")
//        }
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(all = 16.dp)
//        ) {
//            items(dataList.value) { item ->
//                Row {
//                    Text(item.toString())
//                }
//                Spacer(modifier = Modifier.height(10.dp))
//            }
//        }
//    }
//}