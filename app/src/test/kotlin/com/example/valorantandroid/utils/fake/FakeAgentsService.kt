package com.example.valorantandroid.utils.fake

import com.example.valorantandroid.agent.data.model.network.AgentDetailsNetworkModel
import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
import com.example.valorantandroid.agent.data.service.ValorantAgentService
import com.example.valorantandroid.utils.TestUtils

/**
 * FakeAgentsService acting as a test double (a fake) for AgentsRepositoryImpl.
 * This repository takes no arguments in its constructor, this is recommended by google.
 */
class FakeAgentsService : ValorantAgentService {

    override suspend fun getAgents(): AgentsNetworkModel = TestUtils.networkAgents

    override suspend fun getAgentByUuid(uuid: String): AgentDetailsNetworkModel = TestUtils
        .netWorkAgentDetailWithStatus

}