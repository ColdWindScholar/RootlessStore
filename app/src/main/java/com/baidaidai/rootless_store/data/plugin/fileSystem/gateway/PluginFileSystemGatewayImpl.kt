package com.baidaidai.rootless_store.data.plugin.fileSystem.gateway

import android.content.Context
import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.fileSystem.androidFileSystem.AndroidFileSystemCapability
import com.baidaidai.rootless_store.data.plugin.remote.datasource.DownloadPluginPackage
import com.baidaidai.rootless_store.domain.plugin.gateway.PluginFileSystemGateway
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.ByteReadChannel
import java.io.File
import javax.inject.Inject

class PluginFileSystemGatewayImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val downloadPluginPackage: DownloadPluginPackage
): PluginFileSystemGateway{
    private val defaultPluginLocation = File(context.getExternalFilesDir(null),"Plugin")
    private val androidFileSystemCapability = AndroidFileSystemCapability(context)

    // Create
    override fun installPlugin(originFileURI: Uri) {
        _pre_intallPlugin(originFileURI)
    }

    override suspend fun installPluginFromMarket(pluginURI: String, pluginManifestRemote: PluginManifestRemote) {
        val remotePluginContent: ByteReadChannel = downloadPluginPackage.usePluginURI(pluginURI).bodyAsChannel()
        val pluginPackageName = pluginManifestRemote.pluginPackageName
        _pre_intallPlugin(
            originFileByteChannel = remotePluginContent,
            destinationFileName = pluginPackageName
        )
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
    private fun _pre_intallPlugin(originFileByteChannel: ByteReadChannel, destination: File = defaultPluginLocation,destinationFileName: String) {
        if (androidFileSystemCapability.confirmPluginPathExists()){
            androidFileSystemCapability.copyFile(
                originFileByteChannel = originFileByteChannel,
                destination = destination,
                destinationFileName = destinationFileName
            )
        }else{
            androidFileSystemCapability.createFileDir("Plugin")
            _pre_intallPlugin(originFileByteChannel,destination,destinationFileName)
        }
    }
}