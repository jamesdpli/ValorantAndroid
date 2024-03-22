package com.example.valorantandroid.agent.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel
import com.example.valorantandroid.agent.ui.composable.AgentAbility
import com.example.valorantandroid.agent.ui.composable.AgentProfile
import com.example.valorantandroid.agent.ui.composable.AgentRole
import com.example.valorantandroid.agent.ui.viewmodel.AgentDetailsUiState

@Composable
fun AgentDetailsScreen(
    agentDetailsUiState: AgentDetailsUiState,
    modifier: Modifier = Modifier
) {
    when (agentDetailsUiState) {
        is AgentDetailsUiState.Success -> AgentDetails(
            agent = agentDetailsUiState.agent,
            modifier = modifier
        )

        is AgentDetailsUiState.Loading -> CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )

        is AgentDetailsUiState.Error -> Text(
            text = "Error",
            modifier = modifier
        )
    }
}


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
