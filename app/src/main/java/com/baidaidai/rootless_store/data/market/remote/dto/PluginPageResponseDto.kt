package com.baidaidai.rootless_store.data.market.remote.dto

import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import kotlinx.serialization.Serializable

@Serializable
data class PluginPageResponseDto(
    val data: List<PluginItemDto>,
    val meta: MetaDto
)