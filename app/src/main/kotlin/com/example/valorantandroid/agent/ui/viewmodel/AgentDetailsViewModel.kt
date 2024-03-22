package com.example.valorantandroid.agent.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel
import com.example.valorantandroid.core.utils.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AgentDetailsUiState {
    data class Success(val agent: AgentDetailDomainModel) : AgentDetailsUiState
    object Loading : AgentDetailsUiState
    data class Error(val message: String) : AgentDetailsUiState
}

@HiltViewModel
class AgentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: AgentsRepository
) : ViewModel() {

    private val uuidNavArg: String = checkNotNull(
        savedStateHandle[Constants.AGENT_UUID]
    )

    private var _agentDetailsUiState =
        MutableStateFlow<AgentDetailsUiState>(AgentDetailsUiState.Loading)
    val agentDetailsUiState = _agentDetailsUiState.asStateFlow()

    init {
        getAgentDetails(uuidNavArg)
    }

    private fun getAgentDetails(uuid: String) = viewModelScope.launch {
        try {
            val agent = repository.getAgentByUuidFromNetwork(uuid)
            _agentDetailsUiState.update {
                return@update AgentDetailsUiState.Success(agent = agent)
            }
        } catch (e: Exception) {
            _agentDetailsUiState.update {
                return@update AgentDetailsUiState.Error(message = e.message.toString())
            }
        }
    }
}