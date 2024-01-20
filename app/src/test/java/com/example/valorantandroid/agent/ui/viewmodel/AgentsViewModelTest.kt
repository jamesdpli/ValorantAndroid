package com.example.valorantandroid.agent.ui.viewmodel

import com.example.valorantandroid.utils.MainDispatcherRule
import com.example.valorantandroid.utils.TestUtils
import com.example.valorantandroid.utils.fake.FakeAgentsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AgentsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeAgentsRepository = FakeAgentsRepository()

    private val agentsViewModel by lazy {
        AgentsViewModel(repository = fakeAgentsRepository)
    }

    @Test
    fun `WHEN viewModel init THEN expect first state to be Loading`() = runTest {
        val values = mutableListOf<AgentUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            agentsViewModel.agentsScreenUiState.toList(values)
        }

        assertEquals(
            AgentUiState.Loading,
            values.first()
        )
    }

    @Test
    fun `GIVEN no api error WHEN viewModel init THEN assert last state is Success with list`() =
        runTest {
            val values = mutableListOf<AgentUiState>()
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                agentsViewModel.agentsScreenUiState.toList(values)
            }

            assertEquals(
                AgentUiState.Success(agents = TestUtils.fakeDomainAgentsList),
                values.last()
            )
        }

    @Test
    fun `GIVEN an api error WHEN viewModel init Then assert last state is Error with message`() =
        runTest {
            fakeAgentsRepository.setIsApiErrorTrue()

            val values = mutableListOf<AgentUiState>()
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                agentsViewModel.agentsScreenUiState.toList(values)
            }

            assertEquals(
                AgentUiState.Error(message = FakeAgentsRepository.EXCEPTION),
                values.last()
            )
        }
}