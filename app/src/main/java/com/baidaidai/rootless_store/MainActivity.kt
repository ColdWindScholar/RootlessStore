package com.baidaidai.rootless_store

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.baidaidai.rootless_store.ui.screens.RootlessStoreStartScreenContainer
import com.baidaidai.rootless_store.ui.theme.*
import com.baidaidai.rootless_store.data.pluginFileSystem.impl.PluginFileSystemGatewayImpl

val RootLessStoreLocalContext = compositionLocalOf<Context>{
    error("No Context Provide")
}
val OpenDocumentLauncher = compositionLocalOf<ManagedActivityResultLauncher<Array<String>, Uri?>>{
    error("No ActivityResultLauncher Provide")
}
class MainActivity : ComponentActivity(){
    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            val pluginFileSystemImpl = remember(context) {
                PluginFileSystemGatewayImpl(context)
            }

            val openDocumentLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument()
            ) { uri: Uri? ->
                uri?.let { pluginFileSystemImpl.installPlugin(it) }
            }

            RootlessStoreTheme {
                CompositionLocalProvider(
                    RootLessStoreLocalContext provides context,
                    OpenDocumentLauncher provides openDocumentLauncher
                ) {
                    RootlessStoreStartScreenContainer()
                }
            }
        }
    }
}