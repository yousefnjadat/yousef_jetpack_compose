package com.example.jetpack_compose_login_app.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_login_app.data.models.MyDataModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
) {
    val viewModel: HomeViewModel = koinViewModel()
    val homeUiState by viewModel.homeUiState.collectAsState()

    when (homeUiState) {
        is HomeUiState.Init -> {
            // do nothing
        }

        is HomeUiState.Loading -> {
            Text(text = "Loading....")
        }

        is HomeUiState.UserData -> {
            // Use LazyColumn to display a list of cards
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Padding around the list
                verticalArrangement = Arrangement.spacedBy(16.dp) // Spacing between cards
            ) {
                items((homeUiState as HomeUiState.UserData).users) { item ->
                    MyCard(item)
                }
            }
        }

        is HomeUiState.ErrorMessage -> {
            Text(text = (homeUiState as HomeUiState.ErrorMessage).message)
        }

    }

}

@Composable
fun MyCard(item: MyDataModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Make the card take up full width
            .wrapContentHeight(), // Adjust height based on content
        elevation = CardDefaults.cardElevation(8.dp) // Shadow around the card
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Padding inside the card
        ) {
            Text(
                text = "Title: ${item.title}",
                style = MaterialTheme.typography.titleLarge, // Use a larger font for the title
                color = MaterialTheme.colorScheme.primary // Use primary color for the title
            )
            Spacer(modifier = Modifier.height(8.dp)) // Spacing between title and body
            Text(
                text = "Body: ${item.body}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}