package com.baidaidai.rootless_store.domain.source.model

sealed interface PluginSource {
    val sourceRemoteEndpoint: String

    interface PluginSourceUser: PluginSource
    interface PluginSourceDTO: PluginSource {
        val sourceID: String
        val sourceName: String
    }
    interface PluginSourceLocal: PluginSource {
        val sourceID: String
        val sourceName: String
    }

    interface PluginSourceEntity: PluginSource {
        val sourceID: String
        val sourceName: String
    }
}