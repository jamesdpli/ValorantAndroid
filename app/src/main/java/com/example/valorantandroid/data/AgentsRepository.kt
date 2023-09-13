package com.example.valorantandroid.data

import javax.inject.Inject

class AgentsRepository @Inject constructor(
    private val valorantService: ValorantService
) {
    suspend fun getAgents(): AgentsNetworkModel = valorantService.getAgents()

    suspend fun getAgentByUuid(uuid: String): AgentDetailsNetworkModel = valorantService.getAgentByUuid(uuid)
}
