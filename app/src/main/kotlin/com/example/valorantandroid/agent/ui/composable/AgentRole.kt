package com.example.valorantandroid.agent.ui.composable

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel

@Composable
fun AgentRole(
    agent: AgentDetailDomainModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Text(text = agent.role.description)
    }
}
