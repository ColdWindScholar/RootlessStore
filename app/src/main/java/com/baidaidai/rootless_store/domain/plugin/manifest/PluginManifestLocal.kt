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
// PluginTypes: 0 = normal, 1 = env
@Serializable
data class PluginManifestLocal(
    override val Version: String,
    override val RenderingName: String,
    override val PackageName: String,
    override val Type: Int,
    override val ID: String,
    override val iconURI: String?,
    override val author: String,
    override val Description: String,
    override val Dependences: HosterOverallStatus,
    override val entryPoint: String,
    override val ldLibraryPath: List<String>? = null,
    override val env: Map<String, String>? = null
): PluginManifest.PluginManifestLocal{
    companion object {
        val _testOnly_ = PluginManifestLocal(
            Version = "x.x.x",
            RenderingName=  "Test Plugin",
            PackageName = "TestPlugin",
            Type = 0,
            ID = "29bb10c46772264df3c0d0fade57d2eb",
            iconURI = "content://rootless_store/plugin_icon/test",
            author = "Rootless Store(Creater. Bai)",
            Dependences = HosterOverallStatus.LIMITED,
            Description = "Tested by Creater. Bai",
            entryPoint = "./index.sh",
            ldLibraryPath = emptyList(),
            env = emptyMap()
        )
    }
    fun toManifestRoom(): PluginManifestRoom{
        return PluginManifestRoom(
            enabled = false,
            state = PluginState.Great,
            source = PluginSource.Local,
            Version = Version,
            RenderingName = RenderingName,
            PackageName = PackageName,
            Type = Type,
            ID = ID,
            iconURI = iconURI,
            author = author,
            Description = Description,
            Dependences = Dependences,
            entryPoint = entryPoint,
            ldLibraryPath = ldLibraryPath,
            env = env
        )
    }
}