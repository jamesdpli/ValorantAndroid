package com.example.valorantandroid.agent.data.repository

import com.example.valorantandroid.agent.data.dao.AgentDao
import com.example.valorantandroid.agent.data.model.local.AgentEntity
import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
import com.example.valorantandroid.agent.data.service.ValorantAgentService
import com.example.valorantandroid.agent.domain.mapper.toDomainModel
import com.example.valorantandroid.agent.domain.mapper.toEntity
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AgentsRepository {
    suspend fun getAgentsFromNetwork(): List<AgentDomainModel>
    suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel
    suspend fun getAgentsFromDatabase(): List<AgentDomainModel>
    suspend fun insertAgent(agentDomainModel: AgentDomainModel)
    suspend fun deleteAgent(agentDomainModel: AgentDomainModel)
}

class AgentsRepositoryImpl @Inject constructor(
    private val valorantService: ValorantAgentService,
    private val dao: AgentDao,
    private val ioDispatcher: CoroutineDispatcher
) : AgentsRepository {
    override suspend fun getAgentsFromNetwork(): List<AgentDomainModel> =
        withContext(ioDispatcher) {
            valorantService
                .getAgents()
                .agents
                .map(AgentsNetworkModel.Agent::toDomainModel)
        }

    override suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel =
        withContext(ioDispatcher) {
            valorantService
                .getAgentByUuid(uuid)
                .agent
                .toDomainModel()
        }

    override suspend fun getAgentsFromDatabase(): List<AgentDomainModel> =
        withContext(ioDispatcher) {
            dao
                .getAllAgents()
                .map(AgentEntity::toDomainModel)
        }

    override suspend fun insertAgent(agentDomainModel: AgentDomainModel) =
        withContext(ioDispatcher) {
            dao.insertAgent(agentDomainModel.toEntity())
        }

    override suspend fun deleteAgent(agentDomainModel: AgentDomainModel) =
        withContext(ioDispatcher) {
            dao.deleteAgent(agentDomainModel.toEntity())
        }
}
