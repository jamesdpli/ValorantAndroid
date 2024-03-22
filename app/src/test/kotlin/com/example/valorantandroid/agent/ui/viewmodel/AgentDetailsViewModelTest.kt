package com.example.valorantandroid.agent.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.valorantandroid.core.utils.constants.Constants
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
class AgentDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val savedStateHandle = SavedStateHandle(
        mapOf(Constants.NavArgs.AGENT_UUID to TestUtils.fakeDomainAgentOne.uuid)
    )
    private val fakeAgentsRepository = FakeAgentsRepository()

    private val agentDetailsViewModel by lazy {
        AgentDetailsViewModel(
            savedStateHandle = savedStateHandle,
            repository = fakeAgentsRepository
        )
    }

    @Test
    fun `WHEN viewModel init THEN expect first state to be Loading`() = runTest {
        val values = mutableListOf<AgentDetailsUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            agentDetailsViewModel.agentDetailsUiState.toList(values)
        }

        assertEquals(
            AgentDetailsUiState.Loading,
            values.first()
        )
    }

    @Test
    fun `GIVEN no api errorWHEN viewModel init THEN expect last state to be Success with agent`() =
        runTest {
            val values = mutableListOf<AgentDetailsUiState>()
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                agentDetailsViewModel.agentDetailsUiState.toList(values)
            }

            assertEquals(
                AgentDetailsUiState.Success(agent = TestUtils.fakeDomainAgentDetailOne),
                values.last()
            )
        }

    @Test
    fun `GIVEN an api error WHEN viewModel init Then assert last state is Error with message`() =
        runTest {
            fakeAgentsRepository.setIsApiErrorTrue()

            val values = mutableListOf<AgentDetailsUiState>()
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                agentDetailsViewModel.agentDetailsUiState.toList(values)
            }

            assertEquals(
                AgentDetailsUiState.Error(message = FakeAgentsRepository.EXCEPTION),
                values.last()
            )
        }
}