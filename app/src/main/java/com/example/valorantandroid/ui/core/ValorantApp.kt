package com.example.valorantandroid.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.valorantandroid.ui.screens.AgentsScreen
import com.example.valorantandroid.ui.screens.AgentsViewModel

@Composable
fun ValorantApp() {

    val viewModel: AgentsViewModel = hiltViewModel()

    AgentsScreen(agentsUiState = viewModel.agentsScreenUiState.collectAsState().value)
}