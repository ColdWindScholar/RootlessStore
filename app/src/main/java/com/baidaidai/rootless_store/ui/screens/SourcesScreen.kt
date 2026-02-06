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
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SourcesScreen(
    contentPadding: PaddingValues,
){
    val sourcesScreenViewModel = hiltViewModel<RootLessStoreSourceScreenViewModel>()
    val pluginSourceList by sourcesScreenViewModel.sourceList.collectAsState()
    
    val listItemColors = ListItemColors(
        // Normal (variant scheme)
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurface,
        leadingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        trailingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        overlineContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        supportingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,

        // Disabled：不做 copy，不在这里“造颜色”
        // 让组件在 enabled=false 时用 ContentAlpha.disabled 进行衰减
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledContentColor = MaterialTheme.colorScheme.onSurface,
        disabledLeadingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledOverlineContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSupportingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,

        // Selected：用 container/onContainer（不用 copy）
        selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        selectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        selectedLeadingContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        selectedTrailingContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        selectedOverlineContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        selectedSupportingContentColor = MaterialTheme.colorScheme.onSecondaryContainer,

        // Dragged：更像“浮起的那一行”，用 surface/onSurface（不用 copy）
        draggedContainerColor = MaterialTheme.colorScheme.surface,
        draggedContentColor = MaterialTheme.colorScheme.onSurface,
        draggedLeadingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        draggedTrailingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        draggedOverlineContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        draggedSupportingContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    )

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
                                IconButton(
                                    onClick = {}
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.outline_arrow_forward_ios_24),
                                        contentDescription = "go to"
                                    )
                                }
                            },
                            colors = listItemColors,
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
