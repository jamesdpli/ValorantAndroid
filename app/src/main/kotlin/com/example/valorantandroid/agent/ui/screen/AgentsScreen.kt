package com.example.valorantandroid.agent.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.valorantandroid.agent.ui.composable.AgentsList
import com.example.valorantandroid.agent.ui.viewmodel.AgentUiState

@Composable
fun AgentsScreen(
    agentsUiState: AgentUiState,
    onAgentClicked: (uuid: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (agentsUiState) {
        is AgentUiState.Error -> Text(
            text = agentsUiState.message,
            modifier.fillMaxSize()
        )
        is AgentUiState.Loading -> CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
        is AgentUiState.Success -> AgentsList(
            agents = agentsUiState.agents,
            onAgentClicked = onAgentClicked,
            modifier = modifier.fillMaxSize()
        )
    }
}



