package com.example.jetpack_compose_login_app.domain.home

import com.example.jetpack_compose_login_app.data.models.MyDataModel
import com.example.jetpack_compose_login_app.data.netowrk.HomeService
import retrofit2.Response

class HomeRepository(private val homeService: HomeService) {

    suspend fun getData(): Response<List<MyDataModel>> {
        return homeService.getData()
    }

}