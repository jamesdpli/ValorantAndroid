package com.example.valorantandroid.agent.ui.composable

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.valorantandroid.R
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel

@Composable
fun AgentProfile(
    agent: AgentDetailDomainModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        AsyncImage(
            model = agent.fullPortrait,
            placeholder = painterResource(id = R.drawable.baseline_image_24),
            contentDescription = agent.name + "portrait"
        )
        Text(
            text = agent.description,
            textAlign = TextAlign.Center
        )
    }
}