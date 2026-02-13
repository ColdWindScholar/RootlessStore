package com.baidaidai.rootless_store.domain.plugin.error

import kotlinx.serialization.Serializable

@Serializable
data class ManifestError(
    override val errorMessage: String
) : PluginError.ManifestError
