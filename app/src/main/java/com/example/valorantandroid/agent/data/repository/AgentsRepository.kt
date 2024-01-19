package com.example.valorantandroid.agent.data.repository

import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
import com.example.valorantandroid.agent.data.service.ValorantAgentService
import com.example.valorantandroid.agent.domain.mapper.toDomainModel
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import javax.inject.Inject

interface AgentsRepository {
    suspend fun getAgentsFromNetwork(): List<AgentDomainModel>
    suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel
}

class AgentsRepositoryImpl @Inject constructor(
    private val valorantService: ValorantAgentService
) : AgentsRepository {
    override suspend fun getAgentsFromNetwork(): List<AgentDomainModel> = valorantService
        .getAgents()
        .agents
        .map(AgentsNetworkModel.Agent::toDomainModel)

    override suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel = valorantService
        .getAgentByUuid(uuid)
        .agent
        .toDomainModel()

}
