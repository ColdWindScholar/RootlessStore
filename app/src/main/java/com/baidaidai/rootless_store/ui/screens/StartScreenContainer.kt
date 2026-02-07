package com.baidaidai.rootless_store.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baidaidai.rootless_store.R
import com.baidaidai.rootless_store.components.pluginsScreen.PluginScreenNecessaryComponents
import com.baidaidai.rootless_store.components.sourcesScreen.SourcesScreenNecessaryComponents
import com.baidaidai.rootless_store.components.startScreen.components.StartScreenNecessaryComponents
import com.baidaidai.rootless_store.ui.model.RootLessStorePluginScreenViewModel
import com.baidaidai.rootless_store.ui.model.RootLessStoreSourceScreenViewModel
import com.baidaidai.rootless_store.ui.theme.RootlessStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootlessStoreStartScreenContainer(
    pluginScreenViewModel: RootLessStorePluginScreenViewModel = hiltViewModel(),
    sourceScreenViewModel: RootLessStoreSourceScreenViewModel = hiltViewModel()
){
    val pluginInfoCount by pluginScreenViewModel.pluginInfoCount.collectAsState()
    val sourceCount by sourceScreenViewModel.sourceCount.collectAsState()

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

    var alertDialogStatus by rememberSaveable{ mutableStateOf(false) }
    var sourceDomainContent by rememberSaveable{ mutableStateOf("") }

    Scaffold(
        topBar = {
            when(currentDestination){
                "PluginScreen" -> PluginScreenNecessaryComponents.PluginScreenScreenTopAppBar(
                    pluginInfoCount = pluginInfoCount
                )
                "SourcesScreen" -> SourcesScreenNecessaryComponents.SourcesScreenTopAppBar(
                    iconButtonOnClick = {
                        alertDialogStatus = !alertDialogStatus
                    },
                    sourceCount = sourceCount
                )
                else -> StartScreenNecessaryComponents.StartScreenTopAppBar()
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
        }
    ) { contentPadding->
        if (alertDialogStatus){
            AlertDialog(
                onDismissRequest = {
                    alertDialogStatus = !alertDialogStatus
                },
                confirmButton = {
                    Button(
                        onClick = {
                            sourceScreenViewModel.addOneSource(sourceURI = sourceDomainContent)
                            alertDialogStatus = !alertDialogStatus
                        }
                    ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        alertDialogStatus = !alertDialogStatus
                    }) {
                        Text("Cancel")
                    }
                },
                title = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.material_symbols_24px),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add Source",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                        Text(
                            text = "Add a repository to update and discover plugins.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = sourceDomainContent,
                            onValueChange = { newValue -> sourceDomainContent = newValue },
                            label = { Text("Repository URL") },
                            placeholder = { Text("https://example.com") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            )
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
                    contentPadding = contentPadding
                )
            }
            composable(
                route = "SourcesScreen"
            ){
                SourceScreen(
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
