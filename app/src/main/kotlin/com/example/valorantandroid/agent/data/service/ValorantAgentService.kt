package com.example.valorantandroid.agent.data.service

import com.example.valorantandroid.agent.data.model.network.AgentDetailsNetworkModel
import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantAgentService {
    @GET("agents")
    suspend fun getAgents(): AgentsNetworkModel

    @GET("agents/{uuid}")
    suspend fun getAgentByUuid(@Path("uuid") uuid: String): AgentDetailsNetworkModel
}
