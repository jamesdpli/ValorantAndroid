package com.example.valorantandroid.feature.agent.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.valorantandroid.feature.agent.domain.model.AgentDomainModel
import com.example.valorantandroid.feature.agent.ui.viewmodel.AgentsUiState

@Composable
fun AgentsScreen(
    agentsUiState: AgentsUiState,
    onAgentClicked: (uuid: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (agentsUiState) {
        is AgentsUiState.IsLoading -> Text(
            text = "Loading",
            modifier = modifier
                .fillMaxSize()
        )
        is AgentsUiState.Success -> AgentsList(
            agents = agentsUiState.agents,
            onAgentClicked = onAgentClicked,
            modifier = modifier
                .fillMaxSize()
        )
        is AgentsUiState.IsError -> Text(
            text = agentsUiState.message,
            modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun AgentsList(
    agents: List<AgentDomainModel>,
    onAgentClicked: (uuid: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier
    ) {
        items(agents) {
            AgentItem(
                agent = it,
                modifier = Modifier
                    .clickable { onAgentClicked(it.uuid, it.name) }
            )
        }
    }
}

@Composable
fun AgentItem(
    agent: AgentDomainModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        AsyncImage(
            model = agent.displayIcon,
            contentDescription = agent.name + "portrait",
            placeholder = painterResource(id = R.drawable.baseline_broken_image_24),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = agent.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}