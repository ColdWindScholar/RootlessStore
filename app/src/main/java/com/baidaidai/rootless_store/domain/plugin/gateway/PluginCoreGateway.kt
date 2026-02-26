package com.baidaidai.rootless_store.domain.plugin.gateway

import android.net.Uri
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote

    fun installPlugin(originFileURI: Uri): Unit
interface PluginCoreGateway {
    suspend fun installPluginFromMarket(pluginURI: String, pluginManifestRemote: PluginManifestRemote)
    fun uninstallPlugin(pluginPackageName: String): Unit
    /* Not Surely if it's stable  */
    fun readPluginManifest(originFileURI: Uri): PluginManifestLocal
}