package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.baidaidai.rootless_store.components.pluginsScreen.PluginInfosContainer
import com.baidaidai.rootless_store.domain.plugin.model.PluginManifestLocal
import com.baidaidai.rootless_store.ui.model.RootLessStoreSourcesScreenViewModel

@Composable
fun SourcesScreen(
    contentPadding: PaddingValues,
){
    val sourcesScreenViewModel = hiltViewModel<RootLessStoreSourcesScreenViewModel>()
    val renderingList = sourcesScreenViewModel.plugins.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .padding(vertical = 15.dp)
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = renderingList.itemCount
        ){ plugin ->
            PluginInfosContainer(PluginManifestLocal._testOnly_)
        }
    }
}

@Composable
@PreviewLightDark
private fun _SourcesScreenPreview_(){
    Scaffold {
        SourcesScreen(contentPadding = it)
    }
}
