package com.example.valorantandroid.agent.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.valorantandroid.agent.ui.composable.AgentDetails
import com.example.valorantandroid.agent.ui.viewmodel.AgentDetailsUiState

@Composable
fun AgentDetailsScreen(
    agentDetailsUiState: AgentDetailsUiState,
    modifier: Modifier = Modifier
) {
    when (agentDetailsUiState) {
        is AgentDetailsUiState.Success -> AgentDetails(
            agent = agentDetailsUiState.agent,
            modifier = modifier
        )
        is AgentDetailsUiState.Loading -> CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
        is AgentDetailsUiState.Error -> Text(
            text = "Error",
            modifier = modifier
        )
    }
}
