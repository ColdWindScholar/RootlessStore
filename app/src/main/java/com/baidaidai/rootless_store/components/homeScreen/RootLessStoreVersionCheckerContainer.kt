package com.baidaidai.rootless_store.components.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.R

@Composable
fun RootLessStoreVersionCheckerContainer(){
    val cardColors = CardDefaults.cardColors(
        containerColor = colorScheme.primaryContainer,
        contentColor = colorScheme.onPrimaryContainer,
    )
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(),
        colors = cardColors
    ){
        Column(
            modifier = Modifier
                .padding(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.terminal_24px),
                    contentDescription = "Terminal Icon",
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(20.dp)
                )
                Column{
                    Text(
                        text = "RootLessStore is Running",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Version: 0.0.1",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}