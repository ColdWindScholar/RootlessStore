package com.baidaidai.rootless_store.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baidaidai.rootless_store.components.pluginsScreen.PluginScreenNecessaryComponents
import com.baidaidai.rootless_store.components.sourcesScreen.SourcesScreenNecessaryComponents
import com.baidaidai.rootless_store.components.startScreen.StartScreenErrorDialog
import com.baidaidai.rootless_store.components.startScreen.StartScreenRepositoryDialog
import com.baidaidai.rootless_store.components.startScreen.components.StartScreenNecessaryComponents
import com.baidaidai.rootless_store.domain.error.RootlessStoreError
import com.baidaidai.rootless_store.ui.model.RootLessStoreMarketScreenViewModel
import com.baidaidai.rootless_store.ui.model.RootLessStorePluginScreenViewModel
import com.baidaidai.rootless_store.ui.model.RootLessStoreSourceScreenViewModel
import com.baidaidai.rootless_store.ui.theme.RootlessStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootlessStoreStartScreenContainer(
    pluginScreenViewModel: RootLessStorePluginScreenViewModel = hiltViewModel(),
    sourceScreenViewModel: RootLessStoreSourceScreenViewModel = hiltViewModel()
){
    // VM & VM Data
    val marketScreenViewModel = hiltViewModel<RootLessStoreMarketScreenViewModel>()
    val pluginInfoCount by pluginScreenViewModel.pluginInfoCount.collectAsState()
    val sourceCount by sourceScreenViewModel.sourceCount.collectAsState()

    // Navigation
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: "HomeScreen"

    // Define the operation ,which after got the file's URI
    val openDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            pluginScreenViewModel.updateFileURI(uri)
            pluginScreenViewModel.installPlugin()
        }
    }

    // Local Data
    var alertDialogStatus by rememberSaveable{ mutableStateOf(false) }
    var sourceDomainContent by rememberSaveable{ mutableStateOf("") }
    var sharedEvent by rememberSaveable { mutableStateOf<RootlessStoreError?>(null) }

    val scrollBehavior = when(currentDestination){
        "PluginScreen" -> TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        else -> TopAppBarDefaults.enterAlwaysScrollBehavior()
    }

    LaunchedEffect(0) {
        sourceScreenViewModel.sourceEvent.collect { event ->
            sharedEvent = event
        }
    }
    LaunchedEffect(1) {
        pluginScreenViewModel.pluginEvent.collect{ event ->
            sharedEvent = event
        }
    }

    Scaffold(
        topBar = {
            when(currentDestination){
                "PluginScreen" -> PluginScreenNecessaryComponents.PluginScreenScreenTopAppBar(
                    pluginInfoCount = pluginInfoCount,
                    textButtonOnClick = {
                        pluginScreenViewModel.changeBadgeShowStatus()
                    },
                    scrollBehavior = scrollBehavior
                )
                "SourcesScreen" -> SourcesScreenNecessaryComponents.SourcesScreenTopAppBar(
                    iconButtonOnClick = {
                        alertDialogStatus = !alertDialogStatus
                    },
                    textButtonOnClick = {
                        sourceScreenViewModel.changeDeleterShowStatus()
                    },
                    sourceCount = sourceCount
                )
                else -> StartScreenNecessaryComponents.StartScreenTopAppBar(scrollBehavior)
            }
        },
        bottomBar = { StartScreenNecessaryComponents.StartScreenNavigationBar(navController)},
        floatingActionButton = {
            when(currentDestination){
                "PluginScreen" -> {
                    StartScreenNecessaryComponents.StartScreenFloatingButton{
                        openDocumentLauncher.launch(
                            arrayOf(
                                "application/zip",
                            )
                        )
                    }
                }
                else -> {}
            }
        },
        modifier = Modifier
            .nestedScroll(
                connection = scrollBehavior.nestedScrollConnection
            )
    ) { contentPadding->
        if (alertDialogStatus){
            StartScreenRepositoryDialog(
                sourceDomainContent,
                onDismissRequest =  {
                    alertDialogStatus = !alertDialogStatus
                },
                onConfirmButtonClick = {
                    sourceScreenViewModel.addOneSource(sourceURI = sourceDomainContent)
                    alertDialogStatus = !alertDialogStatus
                },
                onDismissButtonClick = {
                    alertDialogStatus = !alertDialogStatus
                },
                onTextFieldValueChange = { newValue -> sourceDomainContent = newValue }
            )
        }
        if (sharedEvent is RootlessStoreError){
            StartScreenErrorDialog(sourceScreenViewModel, sharedEvent)
        }
        NavHost(
            navController = navController,
            startDestination = "HomeScreen"
        ){
            composable(
                route = "HomeScreen"
            ){
                HomeScreen(
                    contentPadding = contentPadding
                )
            }
            composable(
                route = "PluginScreen"
            ){
                RootlessStorePluginScreenContainer(
                    contentPadding = contentPadding,
                    pluginScreenViewModel = pluginScreenViewModel
                )
            }
            composable(
                route = "SourcesScreen"
            ){
                SourceScreen(
                    contentPadding = contentPadding,
                    sourceScreenViewModel = sourceScreenViewModel
                ){ pluginSourceUri ->
                    marketScreenViewModel.updatePluginSourceUri(pluginSourceUri)
                    navController.navigate("MarketScreen")
                }
            }
            composable(
                route = "MarketScreen"
            ){
                MarketScreen(
                    contentPadding = contentPadding,
                    marketScreenViewModel = marketScreenViewModel,
                    navController = navController
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@PreviewLightDark
@Composable
private fun _RootlessStoreStratScreenContainerPrevierer_(){
    RootlessStoreTheme() {
        RootlessStoreStartScreenContainer()
    }
}
