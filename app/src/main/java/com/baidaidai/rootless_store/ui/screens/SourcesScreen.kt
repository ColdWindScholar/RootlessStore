package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.baidaidai.rootless_store.ui.model.RootLessStoreSourceScreenViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SourcesScreen(
    contentPadding: PaddingValues,
){
    val sourcesScreenViewModel = hiltViewModel<RootLessStoreSourceScreenViewModel>()
    val pluginSourceList by sourcesScreenViewModel.sourceList.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(contentPadding)
    ) {
        items(
            items = pluginSourceList
        ){ pluginSource ->
            Column {
                ListItem(
                    onClick = {},
                    supportingContent = {Text(pluginSource.sourceURI)}
                ) {
                    Text(pluginSource.sourceName)
                }
                HorizontalDivider()
            }
        }
    }


//    val renderingList = sourcesScreenViewModel.plugins.collectAsLazyPagingItems()
//    LazyColumn(
//        modifier = Modifier
//            .padding(contentPadding)
//            .fillMaxSize()
//            .padding(vertical = 15.dp)
//            .padding(horizontal = 15.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        items(
//            count = renderingList.itemCount
//        ){ plugin ->
//            val items = renderingList[plugin]
//            PluginInfoContainer(pluginManifest = items!!){}
//        }
//    }
}

//@OptIn(ExperimentalMaterial3ExpressiveApi::class)
//@Composable
//@PreviewLightDark
//private fun _SourcesScreenPreview_(){
//    ListItem(
//        headlineContent = { Text("1") },
//        supportingContent = {Text("2")}
//    )
//}
