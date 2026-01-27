package com.baidaidai.rootless_store.domain.plugin.usecase

import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.data.plugin.fileSystem.impl.PluginFileSystemGatewayImpl
import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoGatewayImpl
import javax.inject.Inject

class InstallOnePluginUseCase @Inject constructor(
    private val pluginFileSystemGatewayImpl: PluginFileSystemGatewayImpl,
    private val repositoryImpl: PluginInfoGatewayImpl
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
        val pluginManiFest = pluginFileSystemGatewayImpl.readPluginManifest(uri)
        val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManiFest)

        pluginFileSystemGatewayImpl.installPlugin(uri)
        repositoryImpl.insertOnePluginInfo(pluginInfoEntity)


    }
}