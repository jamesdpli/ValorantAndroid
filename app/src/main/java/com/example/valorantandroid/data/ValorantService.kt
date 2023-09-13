package com.example.valorantandroid.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantService {
    @GET("agents")
    suspend fun getAgents(): AgentsNetworkModel

    @GET("agents/{uuid}")
    suspend fun getAgentByUuid(@Path("uuid") uuid: String): AgentDetailsNetworkModel
}
