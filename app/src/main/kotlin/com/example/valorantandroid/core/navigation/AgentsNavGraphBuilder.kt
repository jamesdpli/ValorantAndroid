package com.example.valorantandroid.core.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.valorantandroid.agent.ui.screen.AgentDetailsScreen
import com.example.valorantandroid.agent.ui.screen.AgentsScreen
import com.example.valorantandroid.agent.ui.viewmodel.AgentDetailsViewModel
import com.example.valorantandroid.agent.ui.viewmodel.AgentsViewModel
import com.example.valorantandroid.core.utils.constants.Constants

fun NavGraphBuilder.agentsNavGraph(navController: NavController): Unit = navigation(
    route = "agents",
    startDestination = "agentList",
) {
    composable(
        route = "agentList"
    ) {
        val viewModel = hiltViewModel<AgentsViewModel>()
        val agentsUiState by viewModel.agentsScreenUiState.collectAsState()
        AgentsScreen(
            agentsUiState = agentsUiState,
            onAgentClicked = { uuid, name ->
                if (name == "KAY/O") {
                    navController.navigate(
                        route = "agentList/$uuid/${name.replace(oldValue = "/", newValue = "")}"
                    )
                } else {
                    navController.navigate(route = "agentList/$uuid/$name")
                }
            }
        )
    }
    composable(
        route = "agentList/{agentUuid}/{agentName}",
        arguments = listOf(
            navArgument(Constants.NavigationArguments.AGENT_UUID) { type = NavType.StringType },
            navArgument("agentName") { type = NavType.StringType },
        )
    ) {
        val viewModel = hiltViewModel<AgentDetailsViewModel>()
        val agentDetailsUiState by viewModel.agentDetailsUiState.collectAsState()
        AgentDetailsScreen(
            agentDetailsUiState = agentDetailsUiState
        )
    }
}