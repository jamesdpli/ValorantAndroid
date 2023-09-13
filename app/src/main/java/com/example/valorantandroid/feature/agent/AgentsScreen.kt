package com.example.valorantandroid.feature.agent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valorantandroid.data.AgentsNetworkModel.Agent

@Composable
fun AgentsScreen(
    agentsUiState: AgentsUiState,
    onAgentClicked: (uuid: String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    when (agentsUiState) {
        is AgentsUiState.IsLoading -> Text(text = "Loading")
        is AgentsUiState.Success -> AgentsList(agents = agentsUiState.agents, onAgentClicked)
        is AgentsUiState.IsError -> Text(text = agentsUiState.message)
    }
}

@Composable
fun AgentsList(
    agents: List<Agent>,
    onAgentClicked: (uuid: String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(agents) {
            AgentItem(
                agent = it,
                modifier = Modifier
                    .clickable { onAgentClicked(it.uuid) }
            )
        }
    }
}

@Composable
fun AgentItem(
    agent: Agent,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(text = agent.displayName)
    }
}