package com.example.valorantandroid.feature.agent.domain.mapper

import com.example.valorantandroid.feature.agent.data.model.AgentDetailsNetworkModel
import com.example.valorantandroid.feature.agent.data.model.AgentsNetworkModel
import com.example.valorantandroid.feature.agent.domain.model.AgentDomainModel

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