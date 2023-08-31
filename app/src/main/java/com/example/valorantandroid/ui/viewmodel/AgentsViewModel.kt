package com.example.valorantandroid.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.data.AgentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface AgentsUiState {
    data class Success(val agents: String) : AgentsUiState
    object IsLoading : AgentsUiState
    object IsError : AgentsUiState
}

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {
    var agentsScreenUiState: AgentsUiState by mutableStateOf(AgentsUiState.IsLoading)
        private set

    init {
        getAgents()
    }

    fun getAgents() = viewModelScope.launch() {
        try {
            agentsScreenUiState = AgentsUiState.Success(repository.getAgents().agents.toString())
        } catch (e: IOException) {
            AgentsUiState.IsError
        }
    }

}
