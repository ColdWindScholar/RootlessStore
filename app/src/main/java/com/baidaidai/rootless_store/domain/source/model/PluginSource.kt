package com.baidaidai.rootless_store.domain.source.model

sealed interface PluginSource {
    val sourceURI: String

    interface PluginSourceUser: PluginSource
    interface PluginSourceDTO: PluginSource {
        override val sourceURI: String
        val sourceID: String
        val sourceName: String
    }
    interface PluginSourceLocal: PluginSource {
        override val sourceURI: String
        val sourceID: String
        val sourceName: String
    }
}