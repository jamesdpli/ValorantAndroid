package com.example.valorantandroid.agent.data.di

import com.example.valorantandroid.agent.data.repository.AgentsRepository
import com.example.valorantandroid.agent.data.repository.AgentsRepositoryImpl
import com.example.valorantandroid.agent.data.service.ValorantAgentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgentsRepositoryModule {

    @Provides
    @Singleton
    fun provideAgentsRepositoryImpl(
        valorantService: ValorantAgentService
    ): AgentsRepository = AgentsRepositoryImpl(
        valorantService = valorantService
    )

}