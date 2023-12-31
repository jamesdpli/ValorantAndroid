package com.example.valorantandroid.agent.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.valorantandroid.agent.data.model.local.AgentEntity

@Dao
interface AgentDao {
    @Query("SELECT * FROM agents")
    fun getAllAgents(): List<AgentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAgent(agentEntity: AgentEntity)
}