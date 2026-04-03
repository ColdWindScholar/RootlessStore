package com.baidaidai.rootless_store.data.source.gateway

import com.baidaidai.rootless_store.data.source.remote.api.PluginSourceAPI
import com.baidaidai.rootless_store.data.source.remote.dto.PluginSourceDTO
import com.baidaidai.rootless_store.domain.source.gateway.PluginSourceGateway
import io.ktor.client.call.body
import javax.inject.Inject

class PluginSourceGatewayImpl @Inject constructor(
    private val pluginSourceAPI: PluginSourceAPI
): PluginSourceGateway  {

    override suspend fun getPluginSourceMetaInfo(sourceRemoteEndpoint: String): PluginSourceDTO {
        val ktorResponse = pluginSourceAPI.getPluginSourceMetaInfo(sourceRemoteEndpoint)
        val metaData = ktorResponse.body<PluginSourceDTO>()  // Convert JSON to DTO
        return metaData
    }

}