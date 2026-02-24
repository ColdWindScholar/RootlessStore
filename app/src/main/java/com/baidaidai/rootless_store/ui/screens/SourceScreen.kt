package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.R
import com.baidaidai.rootless_store.ui.model.RootLessStoreSourceScreenViewModel
import com.baidaidai.rootless_store.ui.theme.colorscheme.SourceListItemColor
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SourceScreen(
    contentPadding: PaddingValues,
    sourceScreenViewModel: RootLessStoreSourceScreenViewModel,
    onListItemClick:(pluginSourceUri: String)-> Unit
){
    val pluginSourceList by sourceScreenViewModel.sourceList.collectAsState()
    val deleteButtonStatus by sourceScreenViewModel.deleterShowStatus.collectAsState()

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
                            onClick = {onListItemClick(pluginSource.sourceURI)},
                            supportingContent = {Text(pluginSource.sourceURI)},
                            trailingContent = {
                                Icon(
                                    painter = painterResource(R.drawable.outline_arrow_forward_ios_24),
                                    contentDescription = "go to"
                                )
                            },
                            colors = SourceListItemColor(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                            leadingContent = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (deleteButtonStatus){
                                        Row {
                                            IconButton(
                                                onClick = {
                                                    sourceScreenViewModel.deleteOneSource(pluginSource)
                                                },
                                                colors = IconButtonDefaults.iconButtonColors(
                                                    containerColor = BadgeDefaults.containerColor,
                                                    contentColor = contentColorFor(BadgeDefaults.containerColor)
                                                ),
                                                modifier = Modifier
                                                    .size(20.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.outline_close_small_24),
                                                    contentDescription = "Delete",
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(12.dp))
                                        }
                                    }
                                    Image(
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = "2",
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                    )
                                }
                            }
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
