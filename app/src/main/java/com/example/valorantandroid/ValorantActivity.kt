package com.example.valorantandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.valorantandroid.ui.theme.ValorantAndroidTheme
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
                    AgentsPayload()
                }
            }
        }
    }
}

@Composable
fun AgentsPayload(
    viewModel: AgentsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = viewModel.agents.value)
    }
}