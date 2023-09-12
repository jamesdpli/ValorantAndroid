package com.example.valorantandroid.feature.agent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.data.AgentDetailsNetworkModel
import com.example.valorantandroid.data.ValorantApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface AgentDetailsUiState {
    data class Success(val agent: AgentDetailsNetworkModel.Data) : AgentDetailsUiState
    object Loading : AgentDetailsUiState
    object Error : AgentDetailsUiState
}

@HiltViewModel
class AgentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val api: ValorantApi
) : ViewModel() {

    private val uuid: String = checkNotNull(savedStateHandle["agentUuid"])

    private var _agentDetailsUiState = MutableStateFlow<AgentDetailsUiState>(AgentDetailsUiState.Loading)
    val agentDetailsUiState = _agentDetailsUiState.asStateFlow()

    init {
        getAgentDetails(uuid)
    }

    fun getAgentDetails(uuid: String) = viewModelScope.launch {
        try {
            _agentDetailsUiState.value = AgentDetailsUiState.Success(api.getAgentDetails(uuid).data)
        } catch (e: IOException) {
            _agentDetailsUiState.value = AgentDetailsUiState.Error
        }
    }
}