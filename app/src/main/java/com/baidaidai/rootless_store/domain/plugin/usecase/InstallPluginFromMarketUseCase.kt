package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.gateway.PluginCoreGatewayImpl
import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import javax.inject.Inject

class InstallPluginFromMarketUseCase @Inject constructor(
    private val pluginFileSystemGateway: PluginCoreGatewayImpl,
    private val pluginCoreRepositoryImpl: PluginCoreRepositoryImpl
) {
    suspend operator fun invoke(
        pluginURI: String,
        pluginManifestRemote: PluginManifestRemote
    ){
        val pluginManifestRoom = pluginManifestRemote.toManifestRoom()
        val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManifestRoom)

        pluginFileSystemGateway.installPluginFromMarket(pluginURI,pluginManifestRemote)

        pluginCoreRepositoryImpl.insertOnePluginInfo(pluginInfoEntity)
    }
}