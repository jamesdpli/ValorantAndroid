package com.example.valorantandroid.data

import javax.inject.Inject

class AgentsRepository @Inject constructor(
    private val valorantApi: ValorantApi
) {
    suspend fun getAgents(): AgentsNetworkModel = valorantApi.getAgents()

    suspend fun getAgentByUuid(uuid: String): AgentDetailsNetworkModel = valorantApi.getAgentByUuid(uuid)
}
