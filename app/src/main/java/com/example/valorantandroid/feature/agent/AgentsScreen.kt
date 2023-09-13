package com.example.valorantandroid.feature.agent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valorantandroid.R
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
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 200.dp)) {
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
        AsyncImage(
            model = agent.displayIcon,
            contentDescription = agent.developerName + "portrait",
            placeholder = painterResource(id = R.drawable.baseline_broken_image_24),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = agent.displayName,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}