package com.baidaidai.rootless_store.data.plugin.fileSystem.gateway

import android.content.Context
import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.fileSystem.androidFileSystem.AndroidFileSystemCapability
import com.baidaidai.rootless_store.domain.plugin.gateway.PluginFileSystemGateway
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class PluginFileSystemGatewayImpl @Inject constructor(
    @ApplicationContext val context: Context
): PluginFileSystemGateway{
    private val defaultPluginLocation = File(context.getExternalFilesDir(null),"Plugin")
    private val androidFileSystemCapability = AndroidFileSystemCapability(context)

    // Create
    override fun installPlugin(originFileURI: Uri) {
        _pre_intallPlugin(originFileURI)
    }

    // Delete
    override fun uninstallPlugin() {
        TODO("")
    }

    override fun readPluginManifest(originFileURI: Uri): PluginManifestLocal {
        return androidFileSystemCapability.readRawPluginManifest(uri = originFileURI).let {
            androidFileSystemCapability.readManifestJsonContent(it)
        }
    }

    private fun _pre_intallPlugin(originFileURI: Uri, destination: File = defaultPluginLocation) {
        if (androidFileSystemCapability.confirmPluginPathExists()){
            androidFileSystemCapability.copyFile(originFileURI,destination)
        }else{
            androidFileSystemCapability.createFileDir("Plugin")
            _pre_intallPlugin(originFileURI)
        }
    }
}