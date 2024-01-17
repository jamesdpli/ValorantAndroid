package com.example.valorantandroid.agent.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface AgentDetailsUiState {
    data class Success(val agent: AgentDomainModel) : AgentDetailsUiState
    object Loading : AgentDetailsUiState
    object Error : AgentDetailsUiState
}

@HiltViewModel
class AgentDetailsViewModel @Inject constructor(
    private val repository: AgentsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val uuidNavArg: String = checkNotNull(savedStateHandle["agentUuid"])

    private var _agentDetailsUiState = MutableStateFlow<AgentDetailsUiState>(AgentDetailsUiState.Loading)
    val agentDetailsUiState = _agentDetailsUiState.asStateFlow()

    init {
        getAgentDetails(uuidNavArg)
    }

    private fun getAgentDetails(uuid: String) = viewModelScope.launch {
        try {
            _agentDetailsUiState.value =
                AgentDetailsUiState.Success(repository.getAgentByUuidFromNetwork(uuid))
        } catch (e: IOException) {
            _agentDetailsUiState.value = AgentDetailsUiState.Error
        }
    }
}