package com.example.valorantandroid.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.valorantandroid.feature.agent.AgentDetailsScreen
import com.example.valorantandroid.feature.agent.AgentDetailsViewModel
import com.example.valorantandroid.feature.agent.AgentsScreen
import com.example.valorantandroid.feature.agent.AgentsViewModel

@Composable
fun ValorantApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "agents"
    ) {
        agentsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.agentsNavGraph(navController: NavController) {
    navigation(
        route = "agents",
        startDestination = "agentsList"
    ) {
        composable(
            route = "agentsList"
        ) {
            val viewModel = hiltViewModel<AgentsViewModel>()
            val agentsUiState by viewModel.agentsScreenUiState.collectAsState()
            AgentsScreen(
                agentsUiState = agentsUiState,
                onAgentClicked = { navController.navigate("agent/$it") }
            )
        }
        composable(
            route = "agent/{agentUuid}",
            arguments = listOf(navArgument("agentUuid") { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<AgentDetailsViewModel>()
            val agentDetailsUiState by viewModel.agentDetailsUiState.collectAsState()
            AgentDetailsScreen(
                agentDetailsUiState = agentDetailsUiState
            )
        }
    }
}