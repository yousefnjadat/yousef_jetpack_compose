package com.example.jetpack_compose_login_app.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_login_app.data.models.MyDataModel
import com.example.jetpack_compose_login_app.domain.home.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Init)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _homeUiState.value = HomeUiState.Loading
            try {
                val response = homeRepository.getData() // Retrofit call
                if (response.isSuccessful) {
                    response.body()?.let { users ->
                        _homeUiState.value = HomeUiState.UserData(users)
                    }
                }
                // End point
            } catch (e: Exception) {
                _homeUiState.value = HomeUiState.ErrorMessage(e.message.toString())
            }
        }
    }
}


sealed class HomeUiState {
    data object Init : HomeUiState()
    data object Loading : HomeUiState()
    data class ErrorMessage(val message: String) : HomeUiState()
    data class UserData(val users: List<MyDataModel>) : HomeUiState()
}