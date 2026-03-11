package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.components.pluginsScreen.PluginInfoContainerLocal
import com.baidaidai.rootless_store.ui.model.RootLessStorePluginScreenViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.baidaidai.rootless_store.ui.model.RootLessStoreExecuteScreenViewModel

@Composable
fun RootlessStorePluginScreenContainer(
    contentPadding: PaddingValues,
    navController: NavController,
    pluginScreenViewModel: RootLessStorePluginScreenViewModel,
    executeScreenViewModel: RootLessStoreExecuteScreenViewModel
){
    val renderingList by pluginScreenViewModel.pluginInfoList.collectAsState()
    val badgeShowState by pluginScreenViewModel.badgeShowState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            vertical = 15.dp,
            horizontal = 15.dp
        )
    ) {
        items(renderingList){
            PluginInfoContainerLocal(
                pluginManifest = it,
                onSwitchClick = {
                    pluginScreenViewModel.setPluginEnabled(
                        pluginID = it.pluginID,
                        pluginEnabledStatus = !it.enabled
                    )

                    navController.navigate("ExecuteScreen")

                    executeScreenViewModel.executeOnePlugin(it)
                },
                onBadgeClick = {
                    pluginScreenViewModel.uninstallPlugin(it)
                },
                onCardClick = {
                    if (it.enabled){
                        navController.navigate("ExecuteScreen")
                    }
                },
                badgeShowState = badgeShowState
            )
        }
    }
}

//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@PreviewLightDark
//@Composable
//private fun _RootlessStorePluginScreenContainerPreview_(){
//    RootlessStoreTheme {
//        Scaffold {
//            RootlessStorePluginScreenContainer(contentPadding = it)
//        }
//    }
//}