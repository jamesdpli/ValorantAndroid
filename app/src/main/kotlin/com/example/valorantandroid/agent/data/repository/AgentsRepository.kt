package com.example.valorantandroid.agent.data.repository

import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel
import com.example.valorantandroid.agent.domain.model.AgentDomainModel

interface AgentsRepository {
    suspend fun getAgentsFromNetwork(): List<AgentDomainModel>
    suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDetailDomainModel
}