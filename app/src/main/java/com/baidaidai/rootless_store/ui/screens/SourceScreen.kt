package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baidaidai.rootless_store.R
import com.baidaidai.rootless_store.ui.model.RootLessStoreSourceScreenViewModel
import com.baidaidai.rootless_store.ui.theme.colorscheme.SourceListItemColor
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SourceScreen(
    contentPadding: PaddingValues,
){
    val sourcesScreenViewModel = hiltViewModel<RootLessStoreSourceScreenViewModel>()
    val pluginSourceList by sourcesScreenViewModel.sourceList.collectAsState()

    Box(
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 16.dp)
    ) {
        Surface(
            modifier = Modifier.clip(MaterialTheme.shapes.large)
        ) {
            LazyColumn{
                itemsIndexed(
                    items = pluginSourceList
                ){ listIndex, pluginSource ->
                    Column {
                        ListItem(
                            onClick = {},
                            supportingContent = {Text(pluginSource.sourceURI)},
                            trailingContent = {
                                Icon(
                                    painter = painterResource(R.drawable.outline_arrow_forward_ios_24),
                                    contentDescription = "go to"
                                )
                            },
                            colors = SourceListItemColor(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text(pluginSource.sourceName)
                        }
                        if (listIndex!=pluginSourceList.size-1){
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }
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
