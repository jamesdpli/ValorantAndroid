package com.example.valorantandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.data.AgentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AgentsUiState {
    data class Success(val agents: String) : AgentsUiState
    object IsLoading : AgentsUiState
    data class IsError(val message: String) : AgentsUiState
}

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {

    private val _agentsScreenUiState = MutableStateFlow<AgentsUiState>(AgentsUiState.IsLoading)
    val agentsScreenUiState: StateFlow<AgentsUiState> = _agentsScreenUiState.asStateFlow()

    init {
        getAgents()
    }

    fun getAgents() = viewModelScope.launch {
        try {
            _agentsScreenUiState.value = AgentsUiState.Success(repository.getAgents().agents.toString())
        } catch(e: NullPointerException)  {
            _agentsScreenUiState.value = AgentsUiState.IsError(e.message.toString())
        }
    }

}
