package com.example.valorantandroid.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AgentsNetworkDataSource(
    private val valorantApi: ValorantApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAgents(): AgentsNetworkModel = withContext(ioDispatcher) {
        valorantApi.getAgents()
    }
}
