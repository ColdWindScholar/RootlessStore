package com.baidaidai.rootless_store.data.source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PluginSourceDTO(
    val sourceID: String,
    val sourceName: String,
    val sourceURI: String
)