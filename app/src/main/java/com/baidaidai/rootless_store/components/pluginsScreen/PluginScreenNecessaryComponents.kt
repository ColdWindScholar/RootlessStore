package com.baidaidai.rootless_store.components.pluginsScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.baidaidai.rootless_store.R

object PluginScreenNecessaryComponents {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
    @Composable
    fun PluginScreenScreenTopAppBar(
        textButtonOnClick:()-> Unit = {},
        iconButtonOnClick:()-> Unit = {},
        pluginInfoCount: Int = 0,
        scrollBehavior: TopAppBarScrollBehavior
    ){
        LargeFlexibleTopAppBar(
            title = {
                Text("Plugin")
            },
            subtitle = {
                Text(text = "Instead $pluginInfoCount plugin")
            },
            navigationIcon = {
                TextButton(
                    onClick = textButtonOnClick
                ) {
                    Text("Edit")
                }
            },
            actions = {
                IconButton(
                    onClick = iconButtonOnClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.material_symbols_filter_list),
                        contentDescription = "Filter"
                    )
                }
            },
            scrollBehavior = scrollBehavior
        )
    }

    @Composable
    fun PluginScreenFloatingButton(
        onClick:()-> Unit
    ){
        FloatingActionButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_box_add_24),
                contentDescription = "Install"
            )
        }
    }
}