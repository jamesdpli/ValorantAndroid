package com.example.valorantandroid.agent.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AgentUiState {
    object Loading : AgentUiState
    data class Error(val message: String) : AgentUiState
    data class Success(val agents: List<AgentDomainModel>) : AgentUiState
}

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {

    private val _agentsScreenUiState = MutableStateFlow<AgentUiState>(AgentUiState.Loading)
    val agentsScreenUiState: StateFlow<AgentUiState> = _agentsScreenUiState.asStateFlow()

    init {
        getAgents()
    }

    private fun getAgents() = viewModelScope.launch {
        try {
            val networkAgent = repository.getAgentsFromNetwork()
            _agentsScreenUiState.update {
                return@update AgentUiState.Success(agents = networkAgent)
            }
        } catch (e: Exception) {
            _agentsScreenUiState.update {
                return@update AgentUiState.Error(message = e.message.toString())
            }
        }
    }
}
