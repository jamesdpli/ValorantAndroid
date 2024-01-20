package com.example.valorantandroid.agent.ui.domain

import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
import com.example.valorantandroid.agent.domain.mapper.toDomainModel
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import com.example.valorantandroid.utils.TestUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkAgentsMapperExtTest {

    private val mappedAgent = AgentDomainModel(
        uuid = TestUtils.NETWORK_AGENT_UUID,
        name = TestUtils.NETWORK_AGENT_DISPLAY_NAME,
        description = TestUtils.NETWORK_AGENT_DESCRIPTION,
        displayIcon = TestUtils.NETWORK_AGENT_DISPLAY_ICON,
        fullPortrait = TestUtils.NETWORK_AGENT_FULL_PORTRAIT
    )

    @Test
    fun `WHEN toDomainModel on AgentsNetworkModel THEN expect uuid to be mapped`() {
        assertEquals(
            mappedAgent.uuid,
            TestUtils
                .networkAgents
                .agents
                .map(AgentsNetworkModel.Agent::toDomainModel)
                .first()
                .uuid
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentsNetworkModel THEN expect name to be mapped`() {
        assertEquals(
            mappedAgent.name,
            TestUtils
                .networkAgents
                .agents
                .map(AgentsNetworkModel.Agent::toDomainModel)
                .first()
                .name
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentsNetworkModel THEN expect description to be mapped`() {
        assertEquals(
            mappedAgent.description,
            TestUtils
                .networkAgents
                .agents
                .map(AgentsNetworkModel.Agent::toDomainModel)
                .first()
                .description
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentsNetworkModel THEN expect displayIcon to be mapped`() {
        assertEquals(
            mappedAgent.displayIcon,
            TestUtils
                .networkAgents
                .agents
                .map(AgentsNetworkModel.Agent::toDomainModel)
                .first()
                .displayIcon
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentsNetworkModel THEN expect fullPortrait to be mapped`() {
        assertEquals(
            mappedAgent.fullPortrait,
            TestUtils
                .networkAgents
                .agents
                .map(AgentsNetworkModel.Agent::toDomainModel)
                .first()
                .fullPortrait
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentDetailsNetworkModel THEN expect uuid to be mapped`() {
        assertEquals(
            mappedAgent.uuid,
            TestUtils
                .networkAgentDetail
                .toDomainModel()
                .uuid
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentDetailsNetworkModel THEN expect name to be mapped`() {
        assertEquals(
            mappedAgent.name,
            TestUtils
                .networkAgentDetail
                .toDomainModel()
                .name
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentDetailsNetworkModel THEN expect displayIcon to be mapped`() {
        assertEquals(
            mappedAgent.displayIcon,
            TestUtils
                .networkAgentDetail
                .toDomainModel()
                .displayIcon
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentDetailsNetworkModel THEN expect description to be mapped`() {
        assertEquals(
            mappedAgent.description,
            TestUtils
                .networkAgentDetail
                .toDomainModel()
                .description
        )
    }

    @Test
    fun `WHEN toDomainModel on AgentDetailsNetworkModel THEN expect fullPortrait to be mapped`() {
        assertEquals(
            mappedAgent.fullPortrait,
            TestUtils
                .networkAgentDetail
                .toDomainModel()
                .fullPortrait
        )
    }

}