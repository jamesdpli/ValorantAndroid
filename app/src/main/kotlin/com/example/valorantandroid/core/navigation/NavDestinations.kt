package com.example.valorantandroid.core.navigation

enum class NavDestinations(
    val screenTitle: String,
    val destinationName: String
) {
    AGENTS_LIST("Agents", "agentList"),
    AGENT_DETAILS("Agent Details", "agentList/{agentUuid}/{agentName}")
}