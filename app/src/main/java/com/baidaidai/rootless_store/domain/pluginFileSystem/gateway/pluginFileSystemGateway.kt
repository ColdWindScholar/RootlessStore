package com.baidaidai.rootless_store.domain.pluginFileSystem.gateway

import android.net.Uri
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest

interface PluginFileSystemGateway {
    fun installPlugin(originFileURI: Uri): Unit
    fun uninstallPlugin(): Unit
    /* Not Surely if it's stable  */
    fun readPluginManifest(originFileURI: Uri): PluginManiFest
}