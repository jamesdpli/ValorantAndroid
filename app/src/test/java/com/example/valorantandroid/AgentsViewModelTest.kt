package com.example.valorantandroid

import com.example.valorantandroid.agent.data.repository.fake.FakeAgentsRepository
import com.example.valorantandroid.agent.ui.viewmodel.AgentUiState
import com.example.valorantandroid.agent.ui.viewmodel.AgentsViewModel
import com.example.valorantandroid.core.utils.TestUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AgentsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeAgentsRepository = FakeAgentsRepository()

    private val agentsViewModel by lazy {
        AgentsViewModel(fakeAgentsRepository)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `WHEN viewModel init THEN expect first state to be Loading`() = runTest {
        val values = mutableListOf<AgentUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            agentsViewModel.agentsScreenUiState.toList(values)
        }

        Assert.assertEquals(
            AgentUiState.Loading,
            values.first()
        )
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN no api error WHEN viewModel init THEN assert last state is Success with list`() =
        runTest {
            val values = mutableListOf<AgentUiState>()
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                agentsViewModel.agentsScreenUiState.toList(values)
            }

            Assert.assertEquals(
                AgentUiState.Success(agents = TestUtils.fakeAgentsList),
                values.last()
            )
        }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an api error WHEN viewModel init Then assert last state is Error with message`() =
        runTest {
            fakeAgentsRepository.setIsApiErrorTrue()

            val values = mutableListOf<AgentUiState>()
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                agentsViewModel.agentsScreenUiState.toList(values)
            }

            Assert.assertEquals(
                AgentUiState.Error(message = FakeAgentsRepository.EXCEPTION),
                values.last()
            )
        }
}