package com.example.valorantandroid.data

import retrofit2.http.GET

interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(): AgentsNetworkModel
}
