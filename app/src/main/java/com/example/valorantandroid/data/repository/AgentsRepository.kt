package com.example.valorantandroid.data.repository

import com.example.valorantandroid.data.agent.service.ValorantAgentService
import com.example.valorantandroid.feature.agent.domain.mapper.toDomainModel
import com.example.valorantandroid.feature.agent.domain.model.AgentDomainModel
import javax.inject.Inject

class AgentsRepository @Inject constructor(
    private val valorantService: ValorantAgentService
) {
    suspend fun getAgents(): List<AgentDomainModel> = valorantService
        .getAgents()
        .agents
        .map { it.toDomainModel() }

    suspend fun getAgentByUuid(uuid: String): AgentDomainModel = valorantService
        .getAgentByUuid(uuid)
        .agent
        .toDomainModel()
}