package com.example.valorantandroid.agent.data.repository

import com.example.valorantandroid.agent.data.dao.AgentDao
import com.example.valorantandroid.agent.data.model.local.AgentEntity
import com.example.valorantandroid.agent.data.service.ValorantAgentService
import com.example.valorantandroid.agent.domain.mapper.toDomainModel
import com.example.valorantandroid.agent.domain.mapper.toEntity
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import javax.inject.Inject

class AgentsRepository @Inject constructor(
    private val valorantService: ValorantAgentService,
    private val dao: AgentDao
) {
    suspend fun getAgentsFromNetwork(): List<AgentDomainModel> = valorantService
        .getAgents()
        .agents
        .map { it.toDomainModel() }

    suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel = valorantService
        .getAgentByUuid(uuid)
        .agent
        .toDomainModel()

    fun getFavouriteAgents(): List<AgentDomainModel> = dao
        .getAllAgents()
        .map(AgentEntity::toDomainModel)

    fun insertFavouriteAgent(agentDomainModel: AgentDomainModel) = dao
        .insertAgent(agentDomainModel.toEntity())

    fun deleteFavouriteAgent(agentDomainModel: AgentDomainModel) = dao
        .deleteAgent(agentDomainModel.toEntity())
}
