package com.example.valorantandroid.agent.ui.composable

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.valorantandroid.agent.domain.model.AgentDomainModel

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
        items(agents) { AgentItem(agent = it, onAgentClicked) }
    }
}