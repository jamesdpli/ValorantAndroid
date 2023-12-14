package com.example.valorantandroid.data.agent.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agents")
data class AgentEntity(
    @PrimaryKey
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "display_icon")
    val displayIcon: String,
    @ColumnInfo(name = "full_portrait")
    val fullPortrait: String?
)