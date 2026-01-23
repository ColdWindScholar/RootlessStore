package com.baidaidai.rootless_store

import android.app.Application
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
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

val RootLessStoreLocalContext = compositionLocalOf<Context>{
    error("No Context Provide")
}

@HiltAndroidApp
class RootlessStoreApp : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity(){
    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            RootlessStoreTheme {
                CompositionLocalProvider(
                    RootLessStoreLocalContext provides context,
                ) {
                    RootlessStoreStartScreenContainer()
                }
            }
        }
    }
}