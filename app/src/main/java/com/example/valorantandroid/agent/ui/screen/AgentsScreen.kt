package com.example.valorantandroid.agent.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valorantandroid.R
import com.example.valorantandroid.agent.domain.mapper.toEntity
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import com.example.valorantandroid.agent.ui.viewmodel.AgentsUiState

@Composable
fun AgentsScreen(
    agentsUiState: AgentsUiState,
    toggleFavouriteAgent: (AgentDomainModel) -> Unit,
    onAgentClicked: (uuid: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        agentsUiState.isLoading -> Text(
            text = "Loading",
            modifier = modifier
                .fillMaxSize()
        )

        agentsUiState.isSuccess -> AgentsList(
            agents = agentsUiState.agents,
            favouriteAgents = agentsUiState.favouriteAgents,
            toggleFavouriteAgent = toggleFavouriteAgent,
            onAgentClicked = onAgentClicked,
            modifier = modifier
                .fillMaxSize()
        )

        !agentsUiState.errorMessage.isNullOrEmpty() -> Text(
            text = agentsUiState.errorMessage,
            modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun AgentsList(
    agents: List<AgentDomainModel>,
    favouriteAgents: List<AgentDomainModel>,
    toggleFavouriteAgent: (AgentDomainModel) -> Unit,
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
                favouriteAgents = favouriteAgents,
                toggleFavouriteAgent = toggleFavouriteAgent,
                modifier = Modifier
                    .clickable { onAgentClicked(it.uuid, it.name) }
            )
        }
    }
}

@Composable
fun AgentItem(
    agent: AgentDomainModel,
    favouriteAgents: List<AgentDomainModel>,
    toggleFavouriteAgent: (AgentDomainModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        IconButton(onClick = { toggleFavouriteAgent(agent) }) {
            if (favouriteAgents.map { it.toEntity().uuid }.contains(agent.toEntity().uuid)) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favourite")
            } else {
                Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "Favourite")
            }
        }
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