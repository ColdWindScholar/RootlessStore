package com.baidaidai.rootless_store.components.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.R
import com.baidaidai.rootless_store.domain.status.model.RootlessStoreHosterStatus

@Composable
fun RootlessStoreHosterStatusBoard(
    hosterStatus: RootlessStoreHosterStatus
){
    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    )
    Card(
        colors = cardColors,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraSmall)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 30.dp, top = 20.dp, bottom = 10.dp, end = 25.dp)
            ){
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.monitor_heart_24px),
                            contentDescription = "Hoster Status",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .width(10.dp)
                        )
                        Text(
                            text = "Hoster Status",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )
                    AssistChip(
                        enabled = false,
                        onClick = {},
                        label = {
                            Text(
                                text = "Overall: ${hosterStatus.hosterOverallStatus ?: "null"}"
                            )
                        },
                        colors = ChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            leadingIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            trailingIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            disabledLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            disabledLeadingIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            disabledTrailingIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        modifier = Modifier
                            .scale(0.95f)
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(1f)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    HosterStatusCircularProgressRow(
                        label = "Memory",
                        currentValue = hosterStatus.memoryStatus.usedMemory,
                        maxValue = hosterStatus.memoryStatus.totalMemory
                    )
                    HosterStatusCircularProgressRow(
                        label = "Storage",
                        currentValue = hosterStatus.storageStatus.usedStorage,
                        maxValue = hosterStatus.storageStatus.totalStorage
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(2.dp)
        )
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraSmall)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp, vertical = 20.dp)
            ) {
                HosterStatusRow("Version", "${hosterStatus.osAndAPIVersion?.androidVersion} (${hosterStatus.osAndAPIVersion?.apiVersion})")
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                HosterStatusRow("Kernel", hosterStatus.kernelVersion ?: "null")
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                HosterStatusRow("SELinux", hosterStatus.selinuxStatus.toString())
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                HosterStatusRow(
                    "Plugins",
                    "${hosterStatus.pluginStatus.enabledCount}/${hosterStatus.pluginStatus.totalCount}"
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                HosterStatusRow("Temp", hosterStatus.tempStatus?.toString() ?: "null")
            }
        }
    }
}