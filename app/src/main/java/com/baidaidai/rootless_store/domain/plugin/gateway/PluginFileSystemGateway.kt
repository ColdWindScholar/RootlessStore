package com.baidaidai.rootless_store.domain.plugin.gateway

import android.net.Uri
import com.baidaidai.rootless_store.domain.plugin.model.PluginManifestLocal

interface PluginFileSystemGateway {
    fun installPlugin(originFileURI: Uri): Unit
    fun uninstallPlugin(): Unit
    /* Not Surely if it's stable  */
    fun readPluginManifest(originFileURI: Uri): PluginManifestLocal
}