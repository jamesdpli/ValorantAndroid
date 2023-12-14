package com.example.valorantandroid.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.valorantandroid.data.agent.local.AgentDao
import com.example.valorantandroid.data.agent.local.AgentEntity

@Database(
    entities = [AgentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ValorantAppDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
}