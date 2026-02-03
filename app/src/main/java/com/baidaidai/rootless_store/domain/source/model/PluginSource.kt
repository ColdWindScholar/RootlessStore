package com.baidaidai.rootless_store.domain.source.model

import kotlinx.serialization.Serializable

@Serializable
data class PluginSource(
    val sourceID: String,
    val sourceName: String? = null,
    val sourceURI: String
)
