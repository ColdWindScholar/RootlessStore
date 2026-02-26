package com.baidaidai.rootless_store.domain.plugin.usecase

import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.error.PluginError
import javax.inject.Inject

class InstallOnePluginUseCase @Inject constructor(
    private val pluginFileSystemGatewayImpl: PluginFileSystemGatewayImpl,
    private val pluginCoreRepositoryImpl: PluginCoreRepositoryImpl
){
    suspend operator fun invoke(
        uri: Uri,
    ): PluginError?{
        /**
         * 1. Install Plugin
         * 2. Inset DB Content
         * 3. Update VM
         */
        try {
            val pluginManiFest = pluginFileSystemGatewayImpl.readPluginManifest(uri).toManifestRoom()
            val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManiFest)

            pluginFileSystemGatewayImpl.installPlugin(uri)
            repositoryImpl.insertOnePluginInfo(pluginInfoEntity)

            return null
        }catch (error: Throwable){
            val errorStack  = error.stackTrace.OutOfStringLike()


            return PluginError(
                errorMessage = error.message!!,
                errorCause = errorStack
            )
        }

    }
}