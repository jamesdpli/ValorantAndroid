package com.example.valorantandroid.data

class AgentsRepository(
    private val agentsNetworkDataSource: AgentsNetworkDataSource
) {
    suspend fun getAgents(): AgentsNetworkModel = agentsNetworkDataSource.getAgents()
}
