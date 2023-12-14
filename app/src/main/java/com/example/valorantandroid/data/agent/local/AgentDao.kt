package com.example.valorantandroid.data.agent.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AgentDao {
    @Query("SELECT * FROM agents")
    fun getAllAgents(): List<AgentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAgent(agentEntity: AgentEntity)
}