package com.baidaidai.rootless_store.data.market.remote.dto

import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import kotlinx.serialization.Serializable

@Serializable
data class PluginPageResponseDTO(
    val data: List<PluginManifestRemote>,
    val meta: MetaDTO
)