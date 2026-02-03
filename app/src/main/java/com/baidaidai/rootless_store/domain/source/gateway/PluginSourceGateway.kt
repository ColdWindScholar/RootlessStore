package com.baidaidai.rootless_store.domain.source.gateway

import com.baidaidai.rootless_store.data.source.remote.dto.PluginSourceDTO

interface PluginSourceGateway {
    suspend fun getPluginSourceMetaInfo(
        pluginSourceURI: String
    ): PluginSourceDTO
}