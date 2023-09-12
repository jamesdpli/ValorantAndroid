package com.example.valorantandroid.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(): AgentsNetworkModel

    @GET("agents/{uuid}")
    suspend fun getAgentDetails(@Path("uuid") uuid: String): AgentDetailsNetworkModel
}
