package com.example.valorantandroid.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantandroid.data.AgentsNetworkDataSource
import com.example.valorantandroid.data.AgentsRepository
import com.example.valorantandroid.data.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentsViewModel: ViewModel() {

    private val api = Retrofit.valorantApi
    private val source = AgentsNetworkDataSource(api, Dispatchers.IO)
    private val repo = AgentsRepository(source)


    var agents = mutableStateOf("")
        private set

    init {
        getAgents()
    }

    fun getAgents() {
        viewModelScope.launch {
            agents.value = repo.getAgents().data.toString()
        }
    }



}
