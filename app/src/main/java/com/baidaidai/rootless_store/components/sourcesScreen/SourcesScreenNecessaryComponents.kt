package com.baidaidai.rootless_store.components.sourcesScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.baidaidai.rootless_store.R

object SourcesScreenNecessaryComponents {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
    @Composable
    fun SourcesScreenTopAppBar(
        textButtonOnClick:()-> Unit = {},
        iconButtonOnClick:()-> Unit = {}
    ){
        LargeFlexibleTopAppBar(
            title = {
                Text("Sources")
            },
            subtitle = {
                Text(text = "Append 1 Repository")
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
                        painter = painterResource(R.drawable.material_symbols_24px),
                        contentDescription = "Add"
                    )
                }
            }
        )
    }
}