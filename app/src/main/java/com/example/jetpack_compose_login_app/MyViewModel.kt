package com.example.jetpack_compose_login_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getData(): Response<List<MyDataModel>>
}

object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class MyViewModel : ViewModel() {
    private val _data = MutableStateFlow<List<MyDataModel>>(emptyList())
    val data: StateFlow<List<MyDataModel>> = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getData() // Retrofit call
                if (response.isSuccessful) {
                    response.body()?.let {
                        _data.value = it
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }
}
