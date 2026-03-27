package com.baidaidai.rootless_store.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baidaidai.rootless_store.domain.error.RootlessStoreError
import com.baidaidai.rootless_store.ui.model.RootlessStoreShizukuAdbScreenViewModel
import com.baidaidai.rootless_store.ui.theme.RootlessStoreTheme

@Composable
fun ShizukuAdbScreen(
    contentPaddingValues: PaddingValues,
    shizukuAdbScreenViewModel: RootlessStoreShizukuAdbScreenViewModel,
){
    val shizukuActived by shizukuAdbScreenViewModel.shizukuActived.collectAsState()
    val endpointActived by shizukuAdbScreenViewModel.endpointActived.collectAsState()

    val context = LocalContext.current
    val activity = context as? Activity


    LaunchedEffect(endpointActived) {
        if (endpointActived) {
            activity?.finish()
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(contentPaddingValues)
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 15.dp)
    ) {
        item {
            ShizukuAdbScreenOverviewCard()
        }
        item {
            ShizukuAdbScreenActionCard(
                step = "Step 1",
                title = "Request ADB Authorization",
                description = "Grant Rootless Store the ADB authorization it needs before entering the shell flow. Finish this step first so the following connection step can continue normally.",
                buttonText = if (shizukuActived) {
                    "Actived ✓"
                } else {
                    "Request Authorization"
                },
                onClick = {
                    shizukuAdbScreenViewModel.activeShizuku()
                }
            )
        }
        item {
            ShizukuAdbScreenActionCard(
                step = "Step 2",
                title = "Connect to Shizuku UserActivity",
                description = "After ADB authorization is ready, open Shizuku's UserActivity and enter the ADB shell session used by Rootless Store. This is the final step before the user can continue with the shell-based workflow.",
                buttonText = if (endpointActived) {
                    "Actived ✓"
                } else {
                    "Connect to Shizuku"
                },
                onClick = {
                    shizukuAdbScreenViewModel.activeShizukuEndpoint()
                }
            )
        }
    }
}

@Composable
private fun ShizukuAdbScreenOverviewCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Rootless Store ADB",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Some Rootless Store features depend on an ADB shell instead of a full root shell. This page helps the user complete the required setup in the correct order: request ADB authorization first, then connect into Shizuku's ADB shell environment.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ShizukuAdbScreenActionCard(
    step: String,
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = step,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider()
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(buttonText)
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Composable
//@PreviewLightDark
//private fun _ShizukuAdbScreenPreview_(){
//    RootlessStoreTheme {
//        Scaffold { contentPadding ->
//            ShizukuAdbScreen(contentPaddingValues = contentPadding)
//        }
//    }
//}
