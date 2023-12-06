package com.example.valorantandroid.feature.agent.data.repository

import com.example.valorantandroid.feature.agent.data.service.ValorantService
import com.example.valorantandroid.feature.agent.domain.mapper.toDomainModel
import com.example.valorantandroid.feature.agent.domain.model.AgentDomainModel
import javax.inject.Inject

class AgentsRepository @Inject constructor(
    private val valorantService: ValorantService
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
