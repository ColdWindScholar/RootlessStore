package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.fileSystem.gateway.PluginFileSystemGatewayImpl
import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.gateway.PluginFileSystemGateway
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import javax.inject.Inject

class InstallPluginFromMarketUseCase @Inject constructor(
    private val pluginFileSystemGateway: PluginFileSystemGatewayImpl,
    private val pluginInfoRepositoryImpl: PluginInfoRepositoryImpl
) {
    suspend operator fun invoke(
        pluginURI: String,
        pluginManifestRemote: PluginManifestRemote
    ){
        val pluginManifestRoom = pluginManifestRemote.toManifestRoom()
        val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManifestRoom)

        pluginFileSystemGateway.installPluginFromMarket(pluginURI,pluginManifestRemote)

        pluginInfoRepositoryImpl.insertOnePluginInfo(pluginInfoEntity)
    }
}