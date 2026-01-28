package com.baidaidai.rootless_store.domain.plugin.usecase

import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.data.plugin.fileSystem.gateway.PluginFileSystemGatewayImpl
import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import javax.inject.Inject

class InstallOnePluginUseCase @Inject constructor(
    private val pluginFileSystemGatewayImpl: PluginFileSystemGatewayImpl,
    private val repositoryImpl: PluginInfoRepositoryImpl
){
    suspend operator fun invoke(
        uri: Uri,
    ){
        /**
         * 1. Install Plugin
         * 2. Inset DB Content
         * 3. Update VM
         */
        // n
        val pluginManiFest = pluginFileSystemGatewayImpl.readPluginManifest(uri).toManifestRoom()
        val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManiFest)

        pluginFileSystemGatewayImpl.installPlugin(uri)
        repositoryImpl.insertOnePluginInfo(pluginInfoEntity)


    }
}