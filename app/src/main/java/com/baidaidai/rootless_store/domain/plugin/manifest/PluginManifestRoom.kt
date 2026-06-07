package com.baidaidai.rootless_store.domain.plugin.manifest

import com.baidaidai.rootless_store.domain.plugin.model.PluginSource
import com.baidaidai.rootless_store.domain.plugin.model.PluginState
import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus

data class PluginManifestRoom(
    override val enabled: Boolean,
    override val state: PluginState,
    override val source: PluginSource,
    override val Version: String,
    override val RenderingName: String,
    override val PackageName: String,
    override val Type: Int,
    override val ID: String,
    override val iconURI: String?,
    override val author: String,
    override val Description: String,
    override val Condition: HosterOverallStatus,
    override val entryPoint: String,
    override val ldLibraryPath: List<String>?,
    override val env: Map<String, String>?
): PluginManifest.PluginManifestRoom{
    companion object {
        val _testOnly_ = PluginManifestRoom(
            Version = "x.x.x",
            RenderingName = "Test Plugin",
            PackageName = "TestPlugin",
            Type = 0,
            ID = "29bb10c46772264df3c0d0fade57d2eb",
            iconURI = null,
            author = "Rootless Store(Creater. Bai)",
            Condition = HosterOverallStatus.LIMITED,
            Description = "Tested by Creater. Bai",
            enabled = false,
            state = PluginState.PermissionProblems,
            source = PluginSource.Local,
            entryPoint = "./index.sh",
            ldLibraryPath = emptyList(),
            env = emptyMap()
        )
    }
}
