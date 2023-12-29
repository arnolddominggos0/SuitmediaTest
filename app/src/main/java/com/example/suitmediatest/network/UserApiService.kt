package com.example.suitmediatest.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://reqres.in/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UserApiService {
    @GET("api/users?page=1&per_page=10")
    suspend fun getUser(): String
}

object UserApi {
    val service: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}