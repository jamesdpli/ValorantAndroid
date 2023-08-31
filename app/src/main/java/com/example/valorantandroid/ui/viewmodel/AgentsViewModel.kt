package com.example.valorantandroid.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.data.AgentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
): ViewModel() {
    var agents = mutableStateOf("")
        private set

    init {
        getAgents()
    }

    fun getAgents() {
        viewModelScope.launch {
            agents.value = repository.getAgents().data.toString()
        }
    }
}
