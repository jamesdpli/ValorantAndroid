package com.example.valorantandroid.core.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.valorantandroid.core.navigation.agentsNavGraph

@Composable
fun ValorantApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    topAppBarTitleHandler: (State<NavBackStackEntry?>) -> String
) {
    Scaffold(
        topBar = {
            ValorantTopAppBar(
                appBarTitle = topAppBarTitleHandler(navController.currentBackStackEntryAsState()),
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = "agents",
            modifier = Modifier.padding(it)
        ) {
            agentsNavGraph(navController = navController)
        }
    }
}
