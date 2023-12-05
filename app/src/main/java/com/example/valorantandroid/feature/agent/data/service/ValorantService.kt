package com.example.valorantandroid.feature.agent.data.service

import com.example.valorantandroid.feature.agent.data.model.AgentDetailsNetworkModel
import com.example.valorantandroid.feature.agent.data.model.AgentsNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantService {
    @GET("agents")
    suspend fun getAgents(): AgentsNetworkModel

    @GET("agents/{uuid}")
    suspend fun getAgentByUuid(@Path("uuid") uuid: String): AgentDetailsNetworkModel
}
