package com.example.valorantandroid.utils.fake

import com.example.valorantandroid.utils.TestUtils
import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel
import com.example.valorantandroid.agent.domain.model.AgentDomainModel

/**
 * FakeAgentsRepository acting as a test double (a fake) for AgentRepositoryImpl.
 * This repository takes no arguments in its constructor, this is recommended by google.
 * There is an additional function exclusive to this class that allow you to set variables
 * extrinsically so other functions return a desired result.
 */
class FakeAgentsRepository : AgentsRepository {

    private var isApiError = false
    private val agents = TestUtils.fakeDomainAgentsList

    /**
     * FakeAgentsRepository exclusive API for handling the creation of fake api errors
     */
    fun setIsApiErrorTrue() {
        isApiError = true
    }

    override suspend fun getAgentsFromNetwork(): List<AgentDomainModel> = if (!isApiError) {
        agents
    } else {
        throw Exception(EXCEPTION)
    }

    override suspend fun getAgentByUuidFromNetwork(uuid: String): AgentDetailDomainModel =
        if (!isApiError && uuid == TestUtils.fakeDomainAgentOne.uuid) {
            TestUtils.fakeDomainAgentDetailOne
        } else {
            throw Exception(EXCEPTION)
        }


    companion object {
        const val EXCEPTION = "There is an api error!"
    }
}