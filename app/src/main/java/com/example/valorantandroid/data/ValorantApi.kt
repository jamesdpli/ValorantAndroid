package com.example.valorantandroid.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(): AgentsNetworkModel
}

object Retrofit {
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl("https://valorant-api.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val valorantApi: ValorantApi = retrofit.create(ValorantApi::class.java)
}