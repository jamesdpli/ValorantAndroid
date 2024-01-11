package com.example.valorantandroid.agent.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    removeFavouriteAgent: (AgentDomainModel) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        agentsUiState.isLoading -> {
            Text(
                text = "Loading",
                modifier = modifier
                    .fillMaxSize()
            )
        }

        agentsUiState.isSuccess -> {
            Column {
                val tabs = listOf("Agents", "Favourites")

                var tabIndex by remember { mutableIntStateOf(value = 0) }

                TabRow(selectedTabIndex = tabIndex) {
                    tabs.forEachIndexed { index, tabName ->
                        Tab(
                            text = { Text(tabName) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> Agents(
                        agents = agentsUiState.agents,
                        favouriteAgents = agentsUiState.favouriteAgents,
                        toggleFavouriteAgent = toggleFavouriteAgent,
                        onAgentClicked = onAgentClicked,
                        modifier = modifier
                            .fillMaxSize()
                    )

                    1 -> FavouriteAgents(
                        favouriteAgents = agentsUiState.favouriteAgents,
                        onAgentClicked = onAgentClicked,
                        removeFavouriteAgent = removeFavouriteAgent,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }
            }
        }

        !agentsUiState.errorMessage.isNullOrEmpty() -> {
            Text(
                text = agentsUiState.errorMessage,
                modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun FavouriteAgents(
    favouriteAgents: List<AgentDomainModel>,
    onAgentClicked: (uuid: String, name: String) -> Unit,
    removeFavouriteAgent: (AgentDomainModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier
    ) {
        items(favouriteAgents) {
            AgentItem(
                agent = it,
                quickActionIcon = {
                    IconButton(onClick = { removeFavouriteAgent(it) }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
                    }
                },
                modifier = Modifier
                    .clickable { onAgentClicked(it.uuid, it.name) }
            )
        }
    }
}

@Composable
fun Agents(
    agents: List<AgentDomainModel>,
    favouriteAgents: List<AgentDomainModel>,
    toggleFavouriteAgent: (agent: AgentDomainModel) -> Unit,
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
                quickActionIcon = {
                    IconButton(onClick = { toggleFavouriteAgent(it) }) {
                        if (favouriteAgents.map { it.toEntity().uuid }
                                .contains(it.toEntity().uuid)) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favourite"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = "Favourite"
                            )
                        }
                    }
                },
                modifier = Modifier
                    .clickable { onAgentClicked(it.uuid, it.name) }
            )
        }
    }
}

@Composable
fun AgentItem(
    agent: AgentDomainModel,
    quickActionIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        quickActionIcon()
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