package com.example.valorantandroid.agent.ui.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valorantandroid.R
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel

@Composable
fun AgentProfile(
    agent: AgentDetailDomainModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .border(width = Dp.Hairline, color = Color.Black, shape = RoundedCornerShape(5.dp))
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