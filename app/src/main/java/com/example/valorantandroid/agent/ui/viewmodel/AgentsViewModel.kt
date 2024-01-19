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

sealed interface AgentsUiState {
    object Loading : AgentsUiState
    data class Error(val message: String) : AgentsUiState
    data class Success(val agents: List<AgentDomainModel>) : AgentsUiState
}

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {

    private val _agentsScreenUiState = MutableStateFlow<AgentsUiState>(AgentsUiState.Loading)
    val agentsScreenUiState: StateFlow<AgentsUiState> = _agentsScreenUiState.asStateFlow()

    init {
        getAgents()
    }

    private fun getAgents() = viewModelScope.launch {
        val networkAgent = repository.getAgentsFromNetwork()
        try {
            _agentsScreenUiState.update {
                return@update AgentsUiState.Success(agents = networkAgent)
            }
        } catch (e: Exception) {
            _agentsScreenUiState.update {
                return@update AgentsUiState.Error(message = e.message.toString())
            }
        }
    }
}
