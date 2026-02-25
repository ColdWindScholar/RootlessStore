package com.baidaidai.rootless_store.components.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.R
import com.baidaidai.rootless_store.domain.status.model.RootlessStoreHosterStatus

@Composable
fun RootlessStoreHosterStatusBoard(
    hosterStatus: RootlessStoreHosterStatus
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.monitor_heart_24px),
                    contentDescription = "Hoster Status",
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )
                Text(
                    text = "Hoster Status",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = "Overall: ${hosterStatus.hosterOverallStatus}",
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider()
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HosterStatusRow("Kernel", hosterStatus.kernelVersion)
                HosterStatusRow("SELinux", hosterStatus.selinuxStatus.toString())
                HosterStatusRow("Path", hosterStatus.absolutePath)
                HosterStatusRow(
                    "Plugins",
                    "${hosterStatus.pluginStatus.activeCount}/${hosterStatus.pluginStatus.totalCount}"
                )
                HosterStatusProgressRow(
                    label = "RAM",
                    currentValue = hosterStatus.ramStatus.usedRAM,
                    maxValue = hosterStatus.ramStatus.totalRAM
                )
                HosterStatusProgressRow(
                    label = "Storage",
                    currentValue = hosterStatus.storageStatus.usedStorage,
                    maxValue = hosterStatus.storageStatus.totalStorage
                )
                HosterStatusRow("Temp", hosterStatus.tempStatus.toString())
            }
        }
    }
}