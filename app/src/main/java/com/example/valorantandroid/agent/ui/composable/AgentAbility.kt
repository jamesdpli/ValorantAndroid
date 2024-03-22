package com.example.valorantandroid.agent.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AgentAbility(
    abilityName: String,
    abilityDescription: String
) {
    Text(text = abilityName)
    Text(text = abilityDescription)
}