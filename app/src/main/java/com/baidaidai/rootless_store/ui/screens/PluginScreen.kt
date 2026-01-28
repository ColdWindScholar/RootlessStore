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
import com.baidaidai.rootless_store.components.pluginsScreen.PluginInfosContainer
import com.baidaidai.rootless_store.ui.model.RootLessStorePluginScreenViewModel
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RootlessStorePluginScreenContainer(
    contentPadding: PaddingValues
){
    val pluginScreenViewModel = hiltViewModel<RootLessStorePluginScreenViewModel>()
    val renderingList by pluginScreenViewModel.pluginInfoList.collectAsState()
    LazyColumn(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .padding(vertical = 15.dp)
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(renderingList){
            PluginInfosContainer(pluginManifest = it){
                pluginScreenViewModel.setPluginEnabled(
                    pluginID = it.pluginID,
                    pluginEnabledStatus = !it.enabled
                )
            }
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