package com.example.valorantandroid.data.agent.service

import com.example.valorantandroid.data.agent.model.AgentDetailsNetworkModel
import com.example.valorantandroid.data.agent.model.AgentsNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantAgentService {
    @GET("agents")
    suspend fun getAgents(): AgentsNetworkModel

    @GET("agents/{uuid}")
    suspend fun getAgentByUuid(@Path("uuid") uuid: String): AgentDetailsNetworkModel
}
