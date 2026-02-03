package com.baidaidai.rootless_store.data.source.remote.dto

import com.baidaidai.rootless_store.domain.source.model.PluginSource
import kotlinx.serialization.Serializable

@Serializable
data class PluginSourceDTO(
    override val sourceID: String,
    override val sourceName: String,
    override val sourceURI: String
): PluginSource.PluginSourceDTO