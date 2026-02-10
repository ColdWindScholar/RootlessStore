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
    override val requiredEnvironment: HosterOverallStatus,
    override val pluginURI: String
): PluginManifest.PluginManifestRemote {
    companion object {
        val _testOnly_ = PluginManifestRemote(
            installedVersion = "x.x.x",
            pluginRenderingName = "Test Plugin",
            pluginPackageName = "TestPlugin",
            pluginID = "29bb10c46772264df3c0d0fade57d2eb",
            pluginURI = "http://test.only.ai/api/v3/assets/plugin?id=29bb10c46772264df3c0d0fade57d2eb",
            iconURI = "content://rootless_store/plugin_icon/test",
            author = "Rootless Store(Creater. Bai)",
            requiredEnvironment = HosterOverallStatus.LIMITED,
            pluginDescription = "Tested by Creater. Bai"
        )
    }
}
