package com.baidaidai.rootless_store.components.stratScreen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.baidaidai.rootless_store.R
import com.baidaidai.rootless_store.domain.StartScreenNavigationBar.model.NavBarItemSpec

object NecessaryComponents {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StartScreenTopAppBar(){
        TopAppBar(
            title = {
                Text("Rootless Store")
            }
        )
    }

    @Composable
    fun StartScreenNavigationBar(){
        NavigationBar {
            NavigationBarItem(
                selected = true,
                onClick = {},
                icon = {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "Home"
                    )
                },
                label = {
                    Text("Home")
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.outline_extension_24),
                        contentDescription = "Plugins"
                    )
                },
                label = {
                    Text("Plugins")
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.outline_list_alt_24),
                        contentDescription = "Sources"
                    )
                },
                label = {
                    Text("Sources")
                }
            )
        }
    }
}