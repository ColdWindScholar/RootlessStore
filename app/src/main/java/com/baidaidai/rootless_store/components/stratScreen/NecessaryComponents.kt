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
    fun StartScreenNavigationBar(
        navigatorController: NavController
    ) {
        val NavigationBarRenderingList = listOf(
            NavBarItemSpec(
                number = 0,
                pattern = painterResource(R.drawable.outline_home_24),
                contentDeprecated = "Home",
                destination = "HomeScreen"
            ),
            NavBarItemSpec(
                number = 1,
                pattern = painterResource(R.drawable.outline_extension_24),
                contentDeprecated = "Plugin",
                destination = "PluginScreen"
            ),
            NavBarItemSpec(
                number = 2,
                pattern = painterResource(R.drawable.outline_list_alt_24),
                contentDeprecated = "Sources",
                destination = "SourcesScreen"
            )
        )
        var currentSelected by rememberSaveable { mutableIntStateOf(0) }
        NavigationBar {
            NavigationBarRenderingList.forEachIndexed { index, spec ->
                NavigationBarItem(
                    selected = index == currentSelected,
                    onClick = {
                        currentSelected = index
                        navigatorController.navigate(spec.destination)
                    },
                    icon = { Icon(spec.pattern, contentDescription = spec.contentDeprecated) },
                    label = { Text(spec.contentDeprecated) }
                )
            }
        }
    }
}