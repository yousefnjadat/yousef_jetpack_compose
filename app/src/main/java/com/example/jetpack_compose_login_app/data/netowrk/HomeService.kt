
package com.example.jetpack_compose_login_app.data.netowrk

import com.example.jetpack_compose_login_app.data.models.MyDataModel
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("posts")
    suspend fun getData(): Response<List<MyDataModel>>
}
