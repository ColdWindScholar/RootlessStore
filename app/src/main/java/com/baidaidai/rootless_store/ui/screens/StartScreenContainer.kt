package com.baidaidai.rootless_store.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baidaidai.rootless_store.components.sourcesScreen.SourcesScreenNecessaryComponents
import com.baidaidai.rootless_store.components.startScreen.components.StartScreenNecessaryComponents
import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import com.baidaidai.rootless_store.domain.status.model.PluginStatus
import com.baidaidai.rootless_store.domain.status.model.RootlessStoreHosterStatus
import com.baidaidai.rootless_store.domain.status.model.SELinuxStatus
import com.baidaidai.rootless_store.domain.status.model.TempStatus
import com.baidaidai.rootless_store.ui.model.RootLessStorePluginScreenViewModel
import com.baidaidai.rootless_store.ui.model.RootlessStoreStratScreenViewModel
import com.baidaidai.rootless_store.ui.theme.RootlessStoreTheme

@Composable
fun RootlessStoreStartScreenContainer(
    pluginScreenViewModel: RootLessStorePluginScreenViewModel = hiltViewModel()
){
    
    val rootlessStoreStratScreenViewModel: RootlessStoreStratScreenViewModel = viewModel<RootlessStoreStratScreenViewModel>()

    val storageStatus by rootlessStoreStratScreenViewModel.storageStatus.collectAsState()
    val ramStatus by rootlessStoreStratScreenViewModel.ramStatus.collectAsState()

    val rootlessStoreHosterStatus = RootlessStoreHosterStatus(
        hosterOverallStatus = HosterOverallStatus.LIMITED,
        kernelVersion = "Unknown",
        selinuxStatus = SELinuxStatus.Restricted,
        absolutePath = "/data/local/tmp/rootless_store",
        pluginStatus = PluginStatus(activeCount = 0, totalCount = 0),
        ramStatus = ramStatus,
        storageStatus = storageStatus,
        tempStatus = TempStatus.LOW
    )

    val navController = rememberNavController()

    // Define the operation ,which after got the file's URI
    val openDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            pluginScreenViewModel.updateFileURI(uri)
            pluginScreenViewModel.installPlugin()
        }
    }

    Scaffold(
        topBar = { NecessaryComponents.StartScreenTopAppBar() },
        bottomBar = { NecessaryComponents.StartScreenNavigationBar(navController)},
        floatingActionButton = {
            NecessaryComponents.StartScreenFloatingButton{
                openDocumentLauncher.launch(
                    arrayOf(
                        "application/zip",
                    )
                )
            }
        }
    ) { contentPadding->
        NavHost(
            navController = navController,
            startDestination = "HomeScreen"
        ){
            composable(
                route = "HomeScreen"
            ){
                HomeScreen(
                    contentPadding = contentPadding,
                    rootlessStoreHosterStatus = rootlessStoreHosterStatus
                )
            }
            composable(
                route = "PluginScreen"
            ){
                RootlessStorePluginScreenContainer(
                    contentPadding = contentPadding
                )
            }
            composable(
                route = "SourcesScreen"
            ){
                SourcesScreen(
                    contentPadding = contentPadding
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
