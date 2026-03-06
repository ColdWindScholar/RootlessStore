package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baidaidai.rootless_store.components.homeScreen.HowToDevelopRootlessStorePlugin
import com.baidaidai.rootless_store.components.homeScreen.RootLessStoreVersionCheckerContainer
import com.baidaidai.rootless_store.components.homeScreen.RootlessStoreHosterStatusBoard
import com.baidaidai.rootless_store.ui.model.RootLessStoreHomeScreenViewModel

@Composable
fun HomeScreen(
    contentPadding: PaddingValues,
    homeScreenViewModel: RootLessStoreHomeScreenViewModel = hiltViewModel()
){
    val rootlessStoreHosterStatus by homeScreenViewModel.rootlessStoreHosterStatus.collectAsState()

    Column(
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 15.dp)
            .padding(top = 15.dp)
    ) {
        /* Version */
        RootLessStoreVersionCheckerContainer()
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        /* Hoster Status */
        RootlessStoreHosterStatusBoard(
            hosterStatus = rootlessStoreHosterStatus
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        /* How to Make Plugin */
        HowToDevelopRootlessStorePlugin()
    }
}