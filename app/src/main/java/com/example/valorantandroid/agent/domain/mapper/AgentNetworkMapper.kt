package com.example.valorantandroid.agent.domain.mapper

import com.example.valorantandroid.agent.data.model.local.AgentEntity
import com.example.valorantandroid.agent.data.model.network.AgentDetailsNetworkModel
import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
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

fun AgentEntity.toDomainModel(): AgentDomainModel = AgentDomainModel(
    uuid = this.uuid,
    name = this.name,
    description = this.description,
    displayIcon = this.displayIcon,
    fullPortrait = this.fullPortrait
)
fun AgentDomainModel.toEntity(): AgentEntity = AgentEntity(
    uuid = this.uuid,
    name = this.name,
    description = this.description,
    displayIcon = this.displayIcon,
    fullPortrait = this.fullPortrait
)