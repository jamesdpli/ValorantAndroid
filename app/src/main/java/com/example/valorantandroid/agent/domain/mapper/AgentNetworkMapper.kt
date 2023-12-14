package com.example.valorantandroid.agent.domain.mapper

import com.example.valorantandroid.data.agent.model.AgentDetailsNetworkModel
import com.example.valorantandroid.data.agent.model.AgentsNetworkModel
import com.example.valorantandroid.agent.domain.model.AgentDomainModel

fun AgentsNetworkModel.Agent.toDomainModel() : AgentDomainModel = AgentDomainModel(
    uuid = this.uuid,
    name = this.displayName,
    description = this.description,
    displayIcon = this.displayIcon,
    fullPortrait = this.fullPortrait
)

fun AgentDetailsNetworkModel.Agent.toDomainModel(): AgentDomainModel = AgentDomainModel(
    uuid = this.uuid,
    name = this.displayName,
    description = this.description,
    displayIcon = this.displayIcon,
    fullPortrait = this.fullPortrait
)