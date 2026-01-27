package com.baidaidai.rootless_store.data.market.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PluginItemDto(
    val pluginID: String,
    val installedVersion: String,
    val pluginRenderingName: String,
    val pluginPackageName: String,
    val iconURI: String,
    val author: String,
    val enabled: Boolean,
    val requiredEnvironment: String,
    val state: String,
    val source: String
)
