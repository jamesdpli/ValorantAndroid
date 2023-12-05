package com.example.valorantandroid.feature.agent.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.valorantandroid.R
import com.example.valorantandroid.feature.agent.data.model.AgentDetailsNetworkModel.Agent
import com.example.valorantandroid.feature.agent.ui.viewmodel.AgentDetailsUiState

@Composable
fun AgentDetailsScreen(
    agentDetailsUiState: AgentDetailsUiState,
    modifier: Modifier = Modifier
) {
    when (agentDetailsUiState) {
        is AgentDetailsUiState.Success -> AgentDetails(agent = agentDetailsUiState.agent)
        is AgentDetailsUiState.Loading -> Text(text = "Loading", modifier = modifier.fillMaxSize())
        is AgentDetailsUiState.Error -> Text(text = "Error")
    }
}


@Composable
fun AgentDetails(
    agent: Agent,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = agent.displayName,
        )
        AsyncImage(
            model = agent.fullPortrait,
            placeholder = painterResource(id = R.drawable.baseline_broken_image_24),
            contentDescription = agent.displayName + "portrait"
        )
        Text(
            text = agent.description,
            textAlign = TextAlign.Center
        )
    }
}
