package com.example.valorantandroid.agent.ui.composable

import android.provider.Settings.Global
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AgentAbility(
    abilityName: String,
    abilityDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .border(width = Dp.Hairline, color = Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 5.dp)
    ) {
        Text(text = abilityName, fontWeight = FontWeight.Bold)
        Text(text = abilityDescription)
    }
}