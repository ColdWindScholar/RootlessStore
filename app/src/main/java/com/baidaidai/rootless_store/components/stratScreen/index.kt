package com.baidaidai.rootless_store.components.stratScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.components.stratScreen.components.HowToDevelopRootlessStorePlugin
import com.baidaidai.rootless_store.components.stratScreen.components.NecessaryComponents
import com.baidaidai.rootless_store.components.stratScreen.components.RootLessStoreVersionCheckerContainer
import com.baidaidai.rootless_store.components.stratScreen.components.RootlessStoreHosterStatusBoard
import com.baidaidai.rootless_store.ui.theme.*

@Composable
fun RootlessStoreStratScreenContainer(){
    Scaffold(
        topBar = { NecessaryComponents.StartScreenTopAppBar()},
        bottomBar = { NecessaryComponents.StartScreenNavigationBar()}
    ) { contentPadding->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 15.dp)
                .padding(top = 10.dp)
        ) {
            /* Version */
            RootLessStoreVersionCheckerContainer()
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            /* Hoster Status */
            RootlessStoreHosterStatusBoard()
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            /* How to Make Plugin */
            HowToDevelopRootlessStorePlugin()
        }
    }
}



@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@PreviewLightDark
@Composable
private fun _RootlessStoreStratScreenContainerPrevierer_(){
    RootlessStoreTheme() {
        RootlessStoreStratScreenContainer()
    }
}
