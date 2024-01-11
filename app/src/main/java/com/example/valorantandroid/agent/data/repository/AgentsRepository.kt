package com.example.valorantandroid.agent.data.repository

import com.example.valorantandroid.agent.data.dao.AgentDao
import com.example.valorantandroid.agent.data.model.local.AgentEntity
import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
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
        .map(AgentsNetworkModel.Agent::toDomainModel)

    suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel = valorantService
        .getAgentByUuid(uuid)
        .agent
        .toDomainModel()

    fun getAgentsFromDatabase(): List<AgentDomainModel> = dao
        .getAllAgents()
        .map(AgentEntity::toDomainModel)

    fun getFavouriteAgentsFromDatabase(): List<AgentDomainModel> = dao
        .getAllFavouriteAgents()
        .map(AgentEntity::toDomainModel)

    fun getAgentFromDatabaseByUuid(uuid: String): AgentDomainModel = dao
        .getAgentById(uuid)
        .toDomainModel()

    fun insertAllAgents(agentDomainModel: List<AgentDomainModel>) = dao
        .insertAllAgents(agentDomainModel.map { it.toEntity() })

    fun insertAgent(agentDomainModel: AgentDomainModel) = dao
        .insertAgent(agentDomainModel.toEntity())

    fun updateAgent(agentDomainModel: AgentDomainModel) = dao
        .updateAgent(agentDomainModel.toEntity())

    fun deleteAgent(agentDomainModel: AgentDomainModel) = dao
        .deleteAgent(agentDomainModel.toEntity())
}
