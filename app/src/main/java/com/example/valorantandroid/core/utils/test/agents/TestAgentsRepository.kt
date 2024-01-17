package com.example.valorantandroid.core.utils.test.agents

import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import com.example.valorantandroid.core.utils.test.TestUtils


/**
 * TestAgentsRepository acting as a test double (a fake) for AgentRepositoryImpl.
 * This repository takes no arguments in its constructor, this is recommended by google.
 * There are additional functions exclusive to this class that allow you to set variables
 * extrinsically so other functions return a desired result.
 */
class TestAgentsRepository : AgentsRepository {

    private var isApiError = false
    private val agents = TestUtils.fakeAgentsList
    private val favouriteAgents = mutableListOf<AgentDomainModel>()

    /**
     * TestAgentsRepository exclusive API for handling the creation of fake network api errors
     */
    fun setIsApiError(isApiError: Boolean) {
        this.isApiError = isApiError
    }

    /**
     * TestAgentsRepository exclusive API for adding favourite agents
     */
    fun addFavouriteAgent(agentDomainModel: AgentDomainModel) {
        favouriteAgents.add(agentDomainModel)
    }

    override suspend fun getAgentsFromNetwork(): List<AgentDomainModel> = if (!isApiError) {
        agents
    } else {
        throw Exception(EXCEPTION)
    }

    override suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel =
        getAgentsFromNetwork().find { it.uuid == uuid } ?: throw Exception(AGENT_NOT_FOUND)

    override suspend fun getAgentsFromDatabase(): List<AgentDomainModel> = favouriteAgents

    override suspend fun insertAgent(agentDomainModel: AgentDomainModel) {
        favouriteAgents.add(agentDomainModel)
    }

    override suspend fun deleteAgent(agentDomainModel: AgentDomainModel) {
        favouriteAgents.remove(agentDomainModel)
    }

    companion object {
        const val EXCEPTION = "There is an api error!"
        private const val AGENT_NOT_FOUND = "There is an api error!"
    }
}