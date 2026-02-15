package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.fileSystem.gateway.PluginFileSystemGatewayImpl
import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import javax.inject.Inject

class UninstallOnePluginUseCase @Inject constructor(
    private val repositoryImpl: PluginInfoRepositoryImpl,
    private val pluginFileSystemGateway: PluginFileSystemGatewayImpl,
) {
    suspend operator fun invoke(
        pluginManifestRoom: PluginManifestRoom
    ){
        val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManifestRoom)
        val pluginPackageName = pluginInfoEntity.pluginPackageName

        pluginFileSystemGateway.uninstallPlugin(pluginPackageName)
        repositoryImpl.deleteOnePluginInfo(pluginInfoEntity)
    }
}