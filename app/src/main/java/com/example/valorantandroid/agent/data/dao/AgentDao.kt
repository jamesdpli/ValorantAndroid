package com.example.valorantandroid.agent.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.valorantandroid.agent.data.model.local.AgentEntity

@Dao
interface AgentDao {
    @Query("SELECT * FROM favourite_agents")
    fun getAllAgents(): List<AgentEntity>

    @Query("SELECT * FROM favourite_agents WHERE :uuid == uuid")
    fun getAgentById(uuid: String): AgentEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAgent(agentEntity: AgentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAgents(agentEntity: List<AgentEntity>)

    @Update
    fun updateAgent(agentEntity: AgentEntity)

    @Delete
    fun deleteAgent(agentEntity: AgentEntity)
}