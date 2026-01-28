package com.baidaidai.rootless_store.domain.plugin.manifest

import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import kotlinx.serialization.Serializable

@Serializable
data class PluginManifestRemote(
    override val installedVersion: String,
    override val pluginRenderingName: String,
    override val pluginPackageName: String,
    override val pluginID: String,
    override val iconURI: String?,
    override val author: String,
    override val pluginDescription: String,
    override val requiredEnvironment: HosterOverallStatus
): PluginManifest.PluginManifestRemote
