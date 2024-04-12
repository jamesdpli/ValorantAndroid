package com.example.valorantandroid.agent.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valorantandroid.R
import com.example.valorantandroid.agent.domain.model.AgentDomainModel

@Composable
fun AgentItem(
    agent: AgentDomainModel,
    onAgentClicked: (uuid: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 5.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .border(width = Dp.Hairline, color = Color.Black, shape = RoundedCornerShape(5.dp))
            .clickable { onAgentClicked(agent.uuid, agent.name) }
    ) {
        AsyncImage(
            model = agent.displayIcon,
            contentDescription = agent.name + "portrait",
            placeholder = painterResource(id = R.drawable.baseline_image_24),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = agent.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}