package com.baidaidai.rootless_store.domain.plugin.gateway

import android.net.Uri
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote

interface PluginFileSystemGateway {
    fun installPlugin(originFileURI: Uri): Unit
    suspend fun installPluginFromMarket(pluginURI: String, pluginManifestRemote: PluginManifestRemote)
    fun uninstallPlugin(): Unit
    /* Not Surely if it's stable  */
    fun readPluginManifest(originFileURI: Uri): PluginManifestLocal
}