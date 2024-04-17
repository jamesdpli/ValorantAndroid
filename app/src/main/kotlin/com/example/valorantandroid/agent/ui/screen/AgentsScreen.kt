package com.example.valorantandroid.agent.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.valorantandroid.agent.ui.composable.AgentsList
import com.example.valorantandroid.agent.ui.viewmodel.AgentUiState
import com.example.valorantandroid.core.utils.constants.Constants

@Composable
fun AgentsScreen(
    agentsUiState: AgentUiState,
    onAgentClicked: (uuid: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (agentsUiState) {
        is AgentUiState.Error -> Text(
            text = agentsUiState.message,
            modifier
                .fillMaxSize()
                .testTag(Constants.TestTags.AGENT_LIST_ERROR_SCREEN)
        )
        is AgentUiState.Loading -> CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
                .testTag(Constants.TestTags.AGENTS_LIST_LOADING_SCREEN)
        )
        is AgentUiState.Success -> AgentsList(
            agents = agentsUiState.agents,
            onAgentClicked = onAgentClicked,
            modifier = modifier
                .fillMaxSize()
                .testTag(Constants.TestTags.AGENT_LIST_SUCCESS_SCREEN)
        )
    }
}



