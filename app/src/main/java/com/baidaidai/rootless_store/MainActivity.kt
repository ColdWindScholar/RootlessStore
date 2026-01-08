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

val RootLessStoreLocalContext = compositionLocalOf<Context>{
    error("No Context Provide")
}
class MainActivity : ComponentActivity(){
    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootlessStoreTheme {
                CompositionLocalProvider(
                    RootLessStoreLocalContext provides LocalContext.current
                ) {
                    RootlessStoreStartScreenContainer()
                }
            }
        }
    }
}