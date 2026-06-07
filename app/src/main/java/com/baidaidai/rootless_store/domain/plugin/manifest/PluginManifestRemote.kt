package com.baidaidai.rootless_store.domain.plugin.manifest

import com.baidaidai.rootless_store.domain.plugin.model.PluginRunModel
import com.baidaidai.rootless_store.domain.plugin.model.PluginSource
import com.baidaidai.rootless_store.domain.plugin.model.PluginState
import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("PluginManifestRemote")
data class PluginManifestRemote(
    override val Version: String,
    override val RenderingName: String,
    override val PackageName: String,
    override val Type: Int,
    override val ID: String,
    override val iconURI: String?,
    override val author: String,
    override val Description: String,
    override val Condition: HosterOverallStatus,
    override val URI: String,
    override val entryPoint: String,
    override val Dependences: List<String>?,
    override val env: Map<String, String>,
    override val ldLibraryPath: List<String>,
    override val RunModel: PluginRunModel
): PluginManifest.PluginManifestRemote {
    companion object {
        val _testOnly_ = PluginManifestRemote(
            Version = "x.x.x",
            RenderingName = "Test Plugin",
            PackageName = "TestPlugin",
            Type = 0,
            ID = "29bb10c46772264df3c0d0fade57d2eb",
            URI = "http://test.only.ai/api/v3/assets/plugin?id=29bb10c46772264df3c0d0fade57d2eb",
            iconURI = "content://rootless_store/plugin_icon/test",
            author = "Rootless Store(Creater. Bai)",
            Condition = HosterOverallStatus.ADB,
            Description = "Tested by Creater.",
            entryPoint = "./index.sh",
            env = emptyMap(),
            Dependences = emptyList(),
            ldLibraryPath = emptyList(),
            RunModel = PluginRunModel.OneTime,
        )
    }
    fun toManifestRoom(): PluginManifestRoom{
        return PluginManifestRoom(
            enabled = false,
            state = PluginState.Great,
            source = PluginSource.Official,
            Version = Version,
            RenderingName = RenderingName,
            PackageName = PackageName,
            Type = Type,
            ID = ID,
            iconURI = iconURI,
            author = author,
            Description = Description,
            Condition = Condition,
            entryPoint = entryPoint,
            Dependences = Dependences,
            env = env,
            ldLibraryPath = ldLibraryPath
        )
    }
}
