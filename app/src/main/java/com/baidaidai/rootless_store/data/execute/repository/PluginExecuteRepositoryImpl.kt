package com.baidaidai.rootless_store.data.execute.repository

import com.baidaidai.rootless_store.data.execute.gateway.PluginExecuteGatewayImpl
import com.baidaidai.rootless_store.data.fileSystem.gateway.AndroidFileSystemCapabilityGatewayImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PluginExecuteRepositoryImpl @Inject constructor(
    private val pluginExecuteGatewayImpl: PluginExecuteGatewayImpl,
    private val androidFileSystemCapabilityGatewayImpl: AndroidFileSystemCapabilityGatewayImpl
) {
    fun executeOnePlugin(
        pluginManifestRoom: PluginManifestRoom
    ): Flow<String> {
        val pluginExecuteEntryPoint = androidFileSystemCapabilityGatewayImpl.getPluginEntryPoint(pluginManifestRoom)
        return pluginExecuteGatewayImpl.executePluginEntryPoint(pluginExecuteEntryPoint)
    }
}