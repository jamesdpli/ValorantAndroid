package com.example.valorantandroid.feature.agent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.data.AgentDetailsNetworkModel.Agent
import com.example.valorantandroid.data.AgentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface AgentDetailsUiState {
    data class Success(val agent: Agent) : AgentDetailsUiState
    object Loading : AgentDetailsUiState
    object Error : AgentDetailsUiState
}

@HiltViewModel
class AgentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: AgentsRepository
) : ViewModel() {

    private val uuidNavArg: String = checkNotNull(savedStateHandle["agentUuid"])

    private var _agentDetailsUiState = MutableStateFlow<AgentDetailsUiState>(AgentDetailsUiState.Loading)
    val agentDetailsUiState = _agentDetailsUiState.asStateFlow()

    init {
        getAgentDetails(uuidNavArg)
    }

    fun getAgentDetails(uuid: String) = viewModelScope.launch {
        try {
            _agentDetailsUiState.value = AgentDetailsUiState.Success(repository.getAgentByUuid(uuid).agent)
        } catch (e: IOException) {
            _agentDetailsUiState.value = AgentDetailsUiState.Error
        }
    }
}