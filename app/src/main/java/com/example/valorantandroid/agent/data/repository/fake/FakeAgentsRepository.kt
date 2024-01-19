package com.example.valorantandroid.agent.data.repository.fake

import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import com.example.valorantandroid.core.utils.TestUtils

/**
 * FakeAgentsRepository acting as a test double (a fake) for AgentRepositoryImpl.
 * This repository takes no arguments in its constructor, this is recommended by google.
 * There is an additional function exclusive to this class that allow you to set variables
 * extrinsically so other functions return a desired result.
 */
class FakeAgentsRepository : AgentsRepository {

    private var isApiError = false
    private val agents = TestUtils.fakeAgentsList

    /**
     * FakeAgentsRepository exclusive API for handling the creation of fake network api errors
     */
    fun setIsApiErrorTrue() {
        this.isApiError = true
    }

    override suspend fun getAgentsFromNetwork(): List<AgentDomainModel> = if (!isApiError) {
        agents
    } else {
        throw Exception(EXCEPTION)
    }

    override suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDomainModel =
        getAgentsFromNetwork().find { it.uuid == uuid } ?: throw Exception(AGENT_NOT_FOUND)

    companion object {
        const val EXCEPTION = "There is an api error!"
        private const val AGENT_NOT_FOUND = "There is an api error!"
    }
}