package com.example.valorantandroid.agent.domain.model

import com.example.valorantandroid.agent.data.model.network.AgentDetailsNetworkModel.Agent.Role
import com.example.valorantandroid.agent.data.model.network.AgentDetailsNetworkModel.Agent.Ability

data class AgentDetailDomainModel(
    val uuid: String,
    val name: String,
    val description: String,
    val abilities: List<Ability>,
    val fullPortrait: String?
)