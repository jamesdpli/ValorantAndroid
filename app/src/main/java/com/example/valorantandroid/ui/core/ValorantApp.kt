package com.example.valorantandroid.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valorantandroid.ui.screens.AgentsScreen
import com.example.valorantandroid.ui.screens.AgentsViewModel

@Composable
fun ValorantApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "agents"
    ) {
        composable("agents") {
            val agentsUiState by hiltViewModel<AgentsViewModel>()
                .agentsScreenUiState
                .collectAsState()
            AgentsScreen(agentsUiState)
        }
    }
}