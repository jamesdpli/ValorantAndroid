package com.example.valorantandroid.agent.data.di

import com.example.valorantandroid.agent.data.dao.AgentDao
import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.data.repository.AgentsRepositoryImpl
import com.example.valorantandroid.agent.data.service.ValorantAgentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgentsRepositoryModule {

    @Provides
    @Singleton
    fun provideAgentsRepositoryImpl(
        service: ValorantAgentService,
        dao: AgentDao,
        ioDispatcher: CoroutineDispatcher
    ): AgentsRepository = AgentsRepositoryImpl(service, dao, ioDispatcher)
}