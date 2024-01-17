package com.example.valorantandroid

import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import com.example.valorantandroid.agent.ui.viewmodel.AgentsViewModel
import com.example.valorantandroid.core.utils.test.TestUtils
import com.example.valorantandroid.core.utils.test.agents.TestAgentsRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AgentsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeAgentsRepository = TestAgentsRepository()

    private val viewModel by lazy {
        AgentsViewModel(fakeAgentsRepository)
    }

    @Test
    fun `Given no api error WHEN init viewModel THEN expect isLoading state to be true`() {
        //TODO -> Capture when loading state goes true
    }

    @Test
    fun `Given api error WHEN init viewModel THEN expect isLoading state to be true`() {
        //TODO -> Capture when loading state goes true
    }

    @Test
    fun `GIVEN no api error WHEN init viewModel THEN expect isSuccess state is true`() {
        assertTrue(viewModel.agentsScreenUiState.value.isSuccess)
    }

    @Test
    fun `GIVEN no api error WHEN init viewModel THEN expect isLoading state is false`() {
        assertFalse(viewModel.agentsScreenUiState.value.isLoading)
    }

    @Test
    fun `GIVEN no api error WHEN init viewModel THEN expect errorMessage state is null`() {
        assertNull(viewModel.agentsScreenUiState.value.errorMessage)
    }

    @Test
    fun `GIVEN no api error WHEN init viewModel THEN expect agents state to display list`() {
        assertEquals(TestUtils.fakeAgentsList, viewModel.agentsScreenUiState.value.agents)
    }

    @Test
    fun `GIVEN no api error WHEN init viewModel THEN expect favouriteAgents state to be empty`() {
        assertEquals(
            emptyList<AgentDomainModel>(),
            viewModel.agentsScreenUiState.value.favouriteAgents
        )
    }

    @Test
    fun `GIVEN api error WHEN init viewModel THEN expect isSuccess state is false`() {
        fakeAgentsRepository.setIsApiError(isApiError = true)

        assertFalse(viewModel.agentsScreenUiState.value.isSuccess)
    }

    @Test
    fun `GIVEN api error WHEN init viewModel THEN expect isLoading state is false`() {
        fakeAgentsRepository.setIsApiError(isApiError = true)

        assertFalse(viewModel.agentsScreenUiState.value.isLoading)
    }

    @Test
    fun `GIVEN api error WHEN init viewModel THEN expect errorMessage is EXCEPTION`() {
        fakeAgentsRepository.setIsApiError(isApiError = true)

        assertEquals(
            TestAgentsRepository.EXCEPTION,
            viewModel.agentsScreenUiState.value.errorMessage
        )
    }

    @Test
    fun `GIVEN api error WHEN init viewModel THEN expect agents state is empty`() {
        fakeAgentsRepository.setIsApiError(isApiError = true)

        assertEquals(
            emptyList<AgentDomainModel>(),
            viewModel.agentsScreenUiState.value.agents
        )
    }

    @Test
    fun `GIVEN api error WHEN init viewModel THEN expect favouriteAgents state is empty`() {
        fakeAgentsRepository.setIsApiError(isApiError = true)

        assertEquals(
            emptyList<AgentDomainModel>(),
            viewModel.agentsScreenUiState.value.favouriteAgents
        )
    }

    @Test
    fun `GIVEN agent is not in database WHEN toggleFavourite THEN add agent to favouriteAgents`() {
        viewModel.toggleFavourite(agent = TestUtils.fakeAgent)

        assertTrue(
            viewModel.agentsScreenUiState.value.favouriteAgents.contains(TestUtils.fakeAgent)
        )
    }

    @Test
    fun `GIVEN agent is in database WHEN toggleFavourite THEN remove agent from favouriteAgents`() {
        fakeAgentsRepository.addFavouriteAgent(agentDomainModel = TestUtils.fakeAgent)

        viewModel.toggleFavourite(agent = TestUtils.fakeAgent)

        assertFalse(
            viewModel.agentsScreenUiState.value.favouriteAgents.contains(TestUtils.fakeAgent)
        )
    }

    @Test
    fun `GIVEN agent is in db WHEN removeFavouriteAgent THEN remove agent from favouriteAgents`() {
        fakeAgentsRepository.addFavouriteAgent(agentDomainModel = TestUtils.fakeAgent)

        viewModel.removeFavouriteAgent(agent = TestUtils.fakeAgent)

        assertFalse(
            viewModel.agentsScreenUiState.value.favouriteAgents.contains(TestUtils.fakeAgent)
        )
    }
}