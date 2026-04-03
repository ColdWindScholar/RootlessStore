package com.baidaidai.rootless_store.domain.source.model

data class PluginSourceLocal(
    override val sourceRemoteEndpoint: String,
    override val sourceID: String,
    override val sourceName: String
): PluginSource.PluginSourceLocal
