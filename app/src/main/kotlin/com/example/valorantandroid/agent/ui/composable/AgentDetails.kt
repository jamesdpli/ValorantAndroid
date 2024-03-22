package com.example.valorantandroid.agent.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel

@Composable
fun AgentDetails(
    agent: AgentDetailDomainModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AgentRole(agent = agent)
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        AgentProfile(agent = agent)
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Card {
            agent.abilities.forEach {
                AgentAbility(
                    abilityName = it.displayName,
                    abilityDescription = it.description
                )
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
            }
        }
    }
}