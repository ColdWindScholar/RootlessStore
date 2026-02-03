package com.baidaidai.rootless_store.domain.source.model

import kotlinx.serialization.Serializable

@Serializable
data class PluginSourceUser(
    override val sourceURI: String
): PluginSource.PluginSourceUser
