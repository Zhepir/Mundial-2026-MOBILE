package com.example.practicatpi.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    private val json = Json { ignoreUnknownKeys = true }
    val api: MundialApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://6a249616420469ff067b2403.mockapi.io/api/v1/")
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
            .create(MundialApiService::class.java)
    }
}