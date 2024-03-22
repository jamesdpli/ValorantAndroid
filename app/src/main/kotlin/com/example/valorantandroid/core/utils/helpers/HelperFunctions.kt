package com.example.valorantandroid.core.utils.helpers

import androidx.compose.runtime.State
import androidx.navigation.NavBackStackEntry
import com.example.valorantandroid.core.navigation.NavDestinations

object HelperFunctions {
    fun getTopBarTitle(navBackStackEntry: State<NavBackStackEntry?>): String = when (
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