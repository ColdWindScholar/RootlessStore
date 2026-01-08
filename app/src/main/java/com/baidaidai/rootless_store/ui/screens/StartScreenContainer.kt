package com.baidaidai.rootless_store.ui.screens

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baidaidai.rootless_store.components.stratScreen.components.NecessaryComponents
import com.baidaidai.rootless_store.domain.hosterstatus.model.HosterOverallStatus
import com.baidaidai.rootless_store.domain.hosterstatus.model.PluginStatus
import com.baidaidai.rootless_store.domain.hosterstatus.model.RootlessStoreHosterStatus
import com.baidaidai.rootless_store.domain.hosterstatus.model.SELinuxStatus
import com.baidaidai.rootless_store.domain.hosterstatus.model.TempStatus
import com.baidaidai.rootless_store.model.RootlessStoreStratScreenViewModel
import com.baidaidai.rootless_store.ui.theme.RootlessStoreTheme

@Composable
fun RootlessStoreStartScreenContainer(){

    val context: Context = LocalContext.current
    val rootlessStoreStratScreenViewModel: RootlessStoreStratScreenViewModel = viewModel<RootlessStoreStratScreenViewModel>()
    rootlessStoreStratScreenViewModel.prepareViewModel(context)

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

    Scaffold(
        topBar = { NecessaryComponents.StartScreenTopAppBar()},
        bottomBar = { NecessaryComponents.StartScreenNavigationBar()}
    ) { contentPadding->
        HomeScreen(
            contentPadding = contentPadding,
            rootlessStoreHosterStatus = rootlessStoreHosterStatus
        )
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
