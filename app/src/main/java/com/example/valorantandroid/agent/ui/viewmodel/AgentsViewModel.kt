package com.example.valorantandroid.agent.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AgentsUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val agents: List<AgentDomainModel> = emptyList(),
    val favouriteAgents: List<AgentDomainModel> = emptyList()
)

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {

    private val _agentsScreenUiState = MutableStateFlow(AgentsUiState())
    val agentsScreenUiState: StateFlow<AgentsUiState> = _agentsScreenUiState.asStateFlow()

    init {
        getAgents()
    }


    private fun getAgents() = viewModelScope.launch(Dispatchers.IO) {
        _agentsScreenUiState.update { it.copy(isLoading = true) }
        try {

            if (repository.getAgentsFromDatabase().isEmpty()) {
                repository.insertAllAgents(repository.getAgentsFromNetwork())
            }

            _agentsScreenUiState.update { agentsScreenUiState ->
                agentsScreenUiState.copy(
                    isLoading = false,
                    isSuccess = true,
                    agents = repository.getAgentsFromDatabase(),
                    favouriteAgents = repository.getFavouriteAgentsFromDatabase()
                )
            }

        } catch (e: Exception) {
            _agentsScreenUiState.update { agentsScreenUiState ->
                agentsScreenUiState.copy(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = e.message.toString()
                )
            }
        }
    }

    fun toggleFavourite(agent: AgentDomainModel) = viewModelScope.launch(Dispatchers.IO) {
        if (!agent.isFavourite) {
            agent.isFavourite = true
            repository.updateAgent(agent)
            _agentsScreenUiState.update {
                it.copy(favouriteAgents = repository.getFavouriteAgentsFromDatabase())
            }
        } else {
            agent.isFavourite = false
            repository.updateAgent(agent)
            _agentsScreenUiState.update {
                it.copy(favouriteAgents = repository.getFavouriteAgentsFromDatabase())
            }
        }
    }

    fun removeFavouriteAgent(agent: AgentDomainModel) = viewModelScope.launch(Dispatchers.IO) {
        agent.isFavourite = false
        repository.updateAgent(agent)
        _agentsScreenUiState.update {
            it.copy(favouriteAgents = repository.getFavouriteAgentsFromDatabase())
        }
    }
}
