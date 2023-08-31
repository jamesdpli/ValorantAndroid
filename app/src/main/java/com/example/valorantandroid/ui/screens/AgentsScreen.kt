package com.example.valorantandroid.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valorantandroid.data.Agent

@Composable
fun AgentsScreen(
    agentsUiState: AgentsUiState,
    modifier: Modifier = Modifier
) {
    when (agentsUiState) {
        is AgentsUiState.IsLoading -> Text(text = "Loading")
        is AgentsUiState.Success -> AgentsList(agents = agentsUiState.agents)
        is AgentsUiState.IsError -> Text(text = agentsUiState.message)
    }
}

@Composable
fun AgentsList(
    agents: List<Agent>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(agents) {
            AgentItem(agent = it)
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