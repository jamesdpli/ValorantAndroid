package com.example.valorantandroid.feature.agent.data.repository

import com.example.valorantandroid.feature.agent.data.service.ValorantService
import com.example.valorantandroid.feature.agent.data.model.AgentDetailsNetworkModel
import com.example.valorantandroid.feature.agent.data.model.AgentsNetworkModel
import javax.inject.Inject

class AgentsRepository @Inject constructor(
    private val valorantService: ValorantService
) {
    suspend fun getAgents(): AgentsNetworkModel = valorantService.getAgents()

    suspend fun getAgentByUuid(uuid: String): AgentDetailsNetworkModel = valorantService.getAgentByUuid(uuid)
}
