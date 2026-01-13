package com.baidaidai.rootless_store.data.pluginInfoRepository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baidaidai.rootless_store.domain.hosterstatus.model.HosterOverallStatus
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginSource
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginState

@Entity(tableName = "pluginInfo")
data class PluginInfoEntity(
    @PrimaryKey
    val pluginID: String,

    // Plugin Basic Infos
    val installedVersion: String,
    val pluginRenderingName: String,
    val pluginPackageName: String,
    val iconURI: String,
    val author: String,

    // Plugin Runtime Infos
    val enabled: Boolean,
    val requiredEnvironment: HosterOverallStatus,
    val state: PluginState,
    val source: PluginSource,
)