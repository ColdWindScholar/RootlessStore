package com.baidaidai.rootless_store.domain.pluginFileSystem.gateway

import android.net.Uri

interface PluginFileSystemGateway {
    fun installPlugin(originFileURI: Uri): Unit
    fun uninstallPlugin(): Unit

    /* Not Surely if it's stable  */
//    fun readPlugin(): Unit
}