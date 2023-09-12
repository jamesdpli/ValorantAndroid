package com.example.valorantandroid.feature.agent

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AgentDetailsScreen(
    agentDetailsUiState: AgentDetailsUiState,
    modifier: Modifier = Modifier
) {
    when(agentDetailsUiState) {
        is AgentDetailsUiState.Success -> {
            Column {
                Text(text = agentDetailsUiState.agent.displayName)
                Text(text = agentDetailsUiState.agent.background)
                Text(text = agentDetailsUiState.agent.description)
                Text(text = agentDetailsUiState.agent.developerName)
            }
        }
        is AgentDetailsUiState.Loading -> Text(text = "Loading")
        is AgentDetailsUiState.Error -> Text(text = "Error")
    }

}