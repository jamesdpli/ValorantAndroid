package com.example.valorantandroid.core.data.di

import android.app.Application
import androidx.room.Room
import com.example.valorantandroid.core.data.database.ValorantAppDatabase
import com.example.valorantandroid.agent.data.dao.AgentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideValorantAppDatabase(application: Application): ValorantAppDatabase = Room
        .databaseBuilder(
            context = application,
            klass = ValorantAppDatabase::class.java,
            name = "valorant_app_database"
        )
        .build()

    @Provides
    @Singleton
    fun provideAgentDao(valorantAppDatabase: ValorantAppDatabase): AgentDao =
        valorantAppDatabase.agentDao()

}