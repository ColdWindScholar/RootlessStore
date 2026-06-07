package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.gateway.PluginCoreGatewayImpl
import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import javax.inject.Inject

class UninstallOneEnvironmentUseCase @Inject constructor(
    private val repositoryImpl: PluginCoreRepositoryImpl,
    private val pluginFileSystemGateway: PluginCoreGatewayImpl,
) {
    suspend operator fun invoke(
        environmentManifestRoom: PluginManifestRoom
    ){
        val environmentInfoEntity = PluginInfoEntity.fromPluginManifestRoom(environmentManifestRoom)
        val environmentPackageName = environmentInfoEntity.PackageName

        pluginFileSystemGateway.uninstallPlugin(environmentPackageName)
        repositoryImpl.deleteOneEnvironmentInfo(environmentInfoEntity)
    }
}