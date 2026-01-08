package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.components.pluginsScreen.PluginInfosContainer
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest
import com.baidaidai.rootless_store.ui.theme.RootlessStoreTheme

@Composable
fun RootlessStorePluginScreenContainer(
    contentPadding: PaddingValues,
    plugins:List<PluginManiFest> = listOf<PluginManiFest>(PluginManiFest._testOnly_)
){
    LazyColumn(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .padding(vertical = 15.dp)
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(plugins){
            PluginInfosContainer(pluginManiFest = it)
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@PreviewLightDark
@Composable
private fun _RootlessStorePluginScreenContainerPreview_(){
    val _TESTONLY_FakePluginsList_ = listOf<PluginManiFest>(PluginManiFest._testOnly_)
    RootlessStoreTheme {
        Scaffold {
            RootlessStorePluginScreenContainer(contentPadding = it,_TESTONLY_FakePluginsList_)
        }
    }
}