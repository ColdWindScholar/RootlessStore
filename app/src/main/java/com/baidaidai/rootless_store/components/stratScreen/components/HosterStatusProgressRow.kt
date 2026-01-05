package com.baidaidai.rootless_store.components.stratScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun HosterStatusProgressRow(label: String, currentValue: Double,maxValue: Double){
    val currentValueProgress by rememberSaveable { mutableDoubleStateOf(currentValue/maxValue) }
    var currentValuePercentage by rememberSaveable { mutableIntStateOf((currentValueProgress*100).toInt()) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(0.35f)
        )
        Column(
            modifier = Modifier
                .weight(0.5f),
        ) {
            LinearProgressIndicator(
                progress = {
                    currentValueProgress.toFloat()
                },
                drawStopIndicator = {}
            )
        }
        Text(
            text = "$currentValuePercentage %",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.15f),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
@PreviewLightDark
private fun _HosterStatusProgressRowPreview_(){
    Box(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        HosterStatusProgressRow(
            label = "RAM",
            currentValue = 139.32,
            maxValue = 512.00
        )
    }
}