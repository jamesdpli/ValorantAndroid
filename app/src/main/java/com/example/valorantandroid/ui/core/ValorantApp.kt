package com.example.valorantandroid.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.valorantandroid.feature.agent.AgentDetailsScreen
import com.example.valorantandroid.feature.agent.AgentDetailsViewModel
import com.example.valorantandroid.feature.agent.AgentsScreen
import com.example.valorantandroid.feature.agent.AgentsViewModel

@Composable
fun ValorantApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "agents"
    ) {
        composable(
            route = "agents"
        ) {
            val viewModel = hiltViewModel<AgentsViewModel>()
            val agentsUiState by viewModel.agentsScreenUiState.collectAsState()
            AgentsScreen(agentsUiState, {navController.navigate("agent/$it")})
        }
        composable(
            route = "agent/{agentUuid}",
            arguments = listOf(navArgument("agentUuid") { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<AgentDetailsViewModel>()
            val agentDetailsUiState by viewModel.agentDetailsUiState.collectAsState()

            AgentDetailsScreen(agentDetailsUiState = agentDetailsUiState)
        }
    }
}