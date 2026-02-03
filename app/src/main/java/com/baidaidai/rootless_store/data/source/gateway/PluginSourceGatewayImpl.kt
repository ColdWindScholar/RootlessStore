package com.baidaidai.rootless_store.data.source.gateway

import com.baidaidai.rootless_store.data.source.remote.api.PluginSourceAPI
import com.baidaidai.rootless_store.data.source.remote.dto.PluginSourceDTO
import com.baidaidai.rootless_store.domain.source.gateway.PluginSourceGateway
import io.ktor.client.call.body
import javax.inject.Inject

class PluginSourceGatewayImpl @Inject constructor(
    private val pluginSourceAPI: PluginSourceAPI
): PluginSourceGateway  {

    override suspend fun getPluginSourceMetaInfo(pluginSourceURI: String): PluginSourceDTO {
        val ktorResponse = pluginSourceAPI.getPluginSourceMetaInfo(pluginSourceURI = pluginSourceURI)
        val metaData = ktorResponse.body<PluginSourceDTO>()
        return metaData
    }

}