package com.example.valorantandroid.agent.data.repository

import com.example.valorantandroid.agent.domain.mapper.toDomainModel
import com.example.valorantandroid.utils.TestUtils
import com.example.valorantandroid.utils.fake.FakeAgentsService
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class AgentsRepositoryImplTest {

    private val fakeValorantAgentService = FakeAgentsService()

    private val agentsRepository by lazy {
        AgentsRepositoryImpl(fakeValorantAgentService)
    }

    @Test
    fun `WHEN getAgentsFromNetwork THEN expect networkAgent name mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.networkAgents.agents.map { it.toDomainModel().name }.first(),
                agentsRepository.getAgentsFromNetwork().first().name
            )
        }

    @Test
    fun `WHEN getAgentsFromNetwork THEN expect networkAgent fullPortrait mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.networkAgents.agents.map { it.toDomainModel().fullPortrait }.first(),
                agentsRepository.getAgentsFromNetwork().first().fullPortrait
            )
        }

    @Test
    fun `WHEN getAgentsFromNetwork THEN expect networkAgent uuid mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.networkAgents.agents.map { it.toDomainModel().uuid }.first(),
                agentsRepository.getAgentsFromNetwork().first().uuid
            )
        }

    @Test
    fun `WHEN getAgentsFromNetwork THEN expect networkAgent description mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.networkAgents.agents.map { it.toDomainModel().description }.first(),
                agentsRepository.getAgentsFromNetwork().first().description
            )
        }

    @Test
    fun `WHEN getAgentsFromNetwork THEN expect networkAgent displayIcon mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.networkAgents.agents.map { it.toDomainModel().displayIcon }.first(),
                agentsRepository.getAgentsFromNetwork().first().displayIcon
            )
        }

    @Test
    fun `WHEN getAgentByUuidFromNetwork THEN networkAgentDetail name mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.netWorkAgentDetailWithStatus.agent.toDomainModel().name,
                agentsRepository.getAgentByUuidFromNetwork(TestUtils.networkAgentDetail.uuid).name
            )
        }

    @Test
    fun `WHEN getAgentByUuidFromNetwork THEN networkAgentDetail uuid mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.netWorkAgentDetailWithStatus.agent.toDomainModel().uuid,
                agentsRepository.getAgentByUuidFromNetwork(TestUtils.networkAgentDetail.uuid).uuid
            )
        }

    @Test
    fun `WHEN getAgentByUuidFromNetwork THEN networkAgentDetail displayIcon mapped to domainModel`()
        = runTest {
            Assert.assertEquals(
                TestUtils.netWorkAgentDetailWithStatus.agent.toDomainModel().displayIcon,
                agentsRepository
                    .getAgentByUuidFromNetwork(TestUtils.networkAgentDetail.uuid)
                    .displayIcon
            )
        }

    @Test
    fun `WHEN getAgentByUuidFromNetwork THEN networkAgentDetail fullPortrait mapped to domainModel`() =
        runTest {
            Assert.assertEquals(
                TestUtils.netWorkAgentDetailWithStatus.agent.toDomainModel().fullPortrait,
                agentsRepository
                    .getAgentByUuidFromNetwork(TestUtils.networkAgentDetail.uuid)
                    .fullPortrait
            )
        }

    @Test
    fun `WHEN getAgentByUuidFromNetwork THEN networkAgentDetail description mapped to domainModel`()
        = runTest {
            Assert.assertEquals(
                TestUtils.netWorkAgentDetailWithStatus.agent.toDomainModel().description,
                agentsRepository
                    .getAgentByUuidFromNetwork(TestUtils.networkAgentDetail.uuid)
                    .description
            )
        }

}