package com.example.valorantandroid.ui.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.valorantandroid.ValorantTopAppBar
import com.example.valorantandroid.feature.agent.AgentDetailsScreen
import com.example.valorantandroid.feature.agent.AgentDetailsViewModel
import com.example.valorantandroid.feature.agent.AgentsScreen
import com.example.valorantandroid.feature.agent.AgentsViewModel

private enum class NavDestinations(
    val screenTitle: String,
    val destinationName: String
) {
    AGENTS_LIST("Agents", "agentList"),
    AGENT_DETAILS("Agent Details", "agentList/{agentUuid}/{agentName}")
}

@Composable
fun ValorantApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ValorantTopAppBar(
                appBarTitle = getTopBarTitle(navController.currentBackStackEntryAsState()),
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        modifier = modifier
    )
    {
        NavHost(
            navController = navController,
            startDestination = "agents",
            modifier = Modifier
                .padding(it)
        ) {
            agentsNavGraph(
                navController = navController
            )
        }
    }
}

fun NavGraphBuilder.agentsNavGraph(navController: NavController) {
    navigation(
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
                    navController.navigate("agentList/$uuid/$name")
                }
            )
        }
        composable(
            route = "agentList/{agentUuid}/{agentName}",
            arguments = listOf(
                navArgument("agentUuid") { type = NavType.StringType },
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
}

private fun getTopBarTitle(navBackStackEntry: State<NavBackStackEntry?>): String = when (
    navBackStackEntry.value?.destination?.route
) {
    NavDestinations.AGENTS_LIST.destinationName ->
        NavDestinations.AGENTS_LIST.screenTitle
    NavDestinations.AGENT_DETAILS.destinationName ->
        navBackStackEntry.value?.arguments?.getString("agentName").orEmpty()
    else ->
        "Destination name not handled!"
}