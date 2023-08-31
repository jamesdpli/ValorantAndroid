package com.example.valorantandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.valorantandroid.ui.theme.ValorantAndroidTheme
import com.example.valorantandroid.ui.viewmodel.AgentsUiState
import com.example.valorantandroid.ui.viewmodel.AgentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ValorantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgentsScreen()
                }
            }
        }
    }
}

@Composable
fun AgentsScreen(
    viewModel: AgentsViewModel = hiltViewModel()
) {
    val agentsUiState = viewModel.agentsScreenUiState

    AgentsPayload(agentsUiState = agentsUiState, modifier = Modifier)
}

@Composable
fun AgentsPayload(
    agentsUiState: AgentsUiState,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .wrapContentSize()
    ) {
        when (agentsUiState) {
            is AgentsUiState.IsLoading -> Text(
                text = "Loading",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
            is AgentsUiState.Success -> Text(
                text = agentsUiState.agents,
            )
            is AgentsUiState.IsError -> Text(text = "rr")
        }
    }
}