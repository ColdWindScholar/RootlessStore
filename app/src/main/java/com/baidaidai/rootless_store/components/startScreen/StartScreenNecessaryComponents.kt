package com.baidaidai.rootless_store.components.startScreen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.baidaidai.rootless_store.R
import com.baidaidai.rootless_store.domain.plugin.model.NavBarItemSpec

object StartScreenNecessaryComponents {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StartScreenTopAppBar(
        scrollBehavior: TopAppBarScrollBehavior
    ){
        TopAppBar(
            title = {
                Text("Rootless Store")
            },
            scrollBehavior = scrollBehavior
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
        val navBackStackEntry by navigatorController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route ?: "HomeScreen"
        NavigationBar {
            NavigationBarRenderingList.forEachIndexed { index, spec ->
                NavigationBarItem(
                    selected = spec.destination == currentDestination,
                    onClick = {
                        navigatorController.navigate(spec.destination)
                    },
                    icon = { Icon(spec.pattern, contentDescription = spec.contentDeprecated) },
                    label = { Text(spec.contentDeprecated) }
                )
            }
        }
    }

    @Composable
    fun StartScreenFloatingButton(
        onClick:()-> Unit
    ){
        FloatingActionButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(R.drawable.terminal_24px),
                contentDescription = "Terminal"
            )
        }
    }
}