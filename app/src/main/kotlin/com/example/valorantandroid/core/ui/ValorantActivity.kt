package com.example.valorantandroid.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import com.example.valorantandroid.core.navigation.NavDestinations
import com.example.valorantandroid.core.ui.composable.ValorantApp
import com.example.valorantandroid.core.ui.theme.ValorantAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ValorantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ValorantApp(topAppBarTitleHandler = ::handleTopAppBarTitle)
                }
            }
        }
    }

    private fun handleTopAppBarTitle(navBackStackEntry: State<NavBackStackEntry?>): String = when (
        navBackStackEntry.value?.destination?.route
    ) {
        NavDestinations.AGENTS_LIST.destinationName ->
            NavDestinations.AGENTS_LIST.screenTitle

        NavDestinations.AGENT_DETAILS.destinationName ->
            if (navBackStackEntry.value?.arguments?.getString("agentName") == "KAYO") {
                "KAY/O"
            } else {
                navBackStackEntry.value?.arguments?.getString("agentName").orEmpty()
            }

        else ->
            "Destination name not handled!"
    }
}