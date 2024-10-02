package com.example.jetpack_compose_login_app.data.netowrk

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"


fun buildOkHttpClient(
): OkHttpClient {
    val builder = OkHttpClient().newBuilder().apply {
        readTimeout(1, TimeUnit.MINUTES)
        connectTimeout(1, TimeUnit.MINUTES)
    }
    return builder.build()
}

fun buildRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient).build()
}

fun createHomeService(retrofit: Retrofit): HomeService {
    return retrofit.create(HomeService::class.java)
}
