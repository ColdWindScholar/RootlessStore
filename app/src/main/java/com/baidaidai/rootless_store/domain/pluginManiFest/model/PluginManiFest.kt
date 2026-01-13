package com.baidaidai.rootless_store.domain.pluginManiFest.model

import com.baidaidai.rootless_store.domain.hosterstatus.model.HosterOverallStatus
import kotlinx.serialization.Serializable

@Serializable
data class PluginManiFest(
    /**
     * This is an initial version only.
     *
     *     The type must be confirmed manually for now.
     *
     *     In later versions, package parameters will be loaded by reading a JSON manifest (deserialization).
     *
     *     No usable methods are available in the 0.x.x series yet.
     */

    // Plugin Basic Infos
    val installedVersion: String,
    val pluginRenderingName: String,
    val pluginPackageName: String,
    /**
     * You can use any random data you like. Generally,
     *
     *
     * Such as MD5 SHA256 HASH
     *
     * The more random it is, the fewer mistakes you’ll make.
     */
    val pluginID: String,
    val iconURI: String,
    val author: String,

    // Plugin Runtime Infos
    val enabled: Boolean,
    val requiredEnvironment: HosterOverallStatus,
    val state: PluginState,
    val source: PluginSource,
){
    companion object {
        val _testOnly_ = PluginManiFest(
            installedVersion = "x.x.x",
            pluginRenderingName=  "Test Plugin",
            pluginPackageName = "TestPlugin",
            pluginID = "29bb10c46772264df3c0d0fade57d2eb",
            iconURI = "content://rootless_store/plugin_icon/test",
            author = "Rootless Store(Creater. Bai)",
            enabled = false,
            requiredEnvironment = HosterOverallStatus.LIMITED,
            state = PluginState.Great,
            source = PluginSource.Local,
        )
    }
}
