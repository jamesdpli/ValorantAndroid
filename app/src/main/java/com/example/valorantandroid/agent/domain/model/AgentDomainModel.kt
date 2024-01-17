package com.example.valorantandroid.agent.domain.model

data class AgentDomainModel(
    val uuid: String,
    val name: String,
    val description: String,
    val displayIcon: String,
    val fullPortrait: String?
)