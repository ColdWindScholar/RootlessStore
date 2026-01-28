package com.baidaidai.rootless_store.domain.plugin.manifest

import com.baidaidai.rootless_store.domain.plugin.model.PluginSource
import com.baidaidai.rootless_store.domain.plugin.model.PluginState
import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import kotlinx.serialization.Serializable

/**
 * Plugin manifest / metadata model.
 *
 * ⚠️ Status: **initial version only**
 *
 * - Some types/fields must still be confirmed manually in this stage.
 * - In later versions, these parameters will be loaded from a JSON manifest
 *   (via deserialization).
 * - The `0.x.x` series is **data-only** for now; do not rely on stable APIs yet.
 *
 * Conventions:
 * - Keep this class focused on **static plugin metadata**.
 * - Runtime state (enabled/state/source, etc.) should live in a separate model
 *   (e.g., DB/runtime entity), not inside this manifest.
 */
@Serializable
data class PluginManifestLocal(
    override val installedVersion: String,
    override val pluginRenderingName: String,
    override val pluginPackageName: String,
    override val pluginID: String,
    override val iconURI: String?,
    override val author: String,
    override val pluginDescription: String,
    override val requiredEnvironment: HosterOverallStatus
): PluginManifest.PluginManifestLocal{
    companion object {
        val _testOnly_ = PluginManifestLocal(
            installedVersion = "x.x.x",
            pluginRenderingName=  "Test Plugin",
            pluginPackageName = "TestPlugin",
            pluginID = "29bb10c46772264df3c0d0fade57d2eb",
            iconURI = "content://rootless_store/plugin_icon/test",
            author = "Rootless Store(Creater. Bai)",
            requiredEnvironment = HosterOverallStatus.LIMITED,
            pluginDescription = "Tested by Creater. Bai"
        )
    }
    fun toManifestRoom(): PluginManifestRoom{
        return PluginManifestRoom(
            enabled = false,
            state = PluginState.Great,
            source = PluginSource.Local,
            installedVersion = installedVersion,
            pluginRenderingName = pluginRenderingName,
            pluginPackageName = pluginPackageName,
            pluginID = pluginID,
            iconURI = iconURI,
            author = author,
            pluginDescription = pluginDescription,
            requiredEnvironment = requiredEnvironment
        )
    }
}