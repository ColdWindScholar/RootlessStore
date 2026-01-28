package com.baidaidai.rootless_store.data.plugin.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import com.baidaidai.rootless_store.domain.plugin.model.PluginSource
import com.baidaidai.rootless_store.domain.plugin.model.PluginState

@Entity(tableName = "pluginInfo")
data class PluginInfoEntity(

    /**
     * pluginID is primaryKey
     *
     * See more infos
     * @example com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginMainFest
     */
    @PrimaryKey
    val pluginID: String,

    // Plugin Basic Infos
    val installedVersion: String,
    val pluginRenderingName: String,
    val pluginPackageName: String,
    val iconURI: String,
    val author: String,
    val pluginDescription:String,

    // Plugin Runtime Infos
    val enabled: Boolean,
    val requiredEnvironment: HosterOverallStatus,
    val state: PluginState,
    val source: PluginSource,
){
    companion object {

        /**
         * Create a PluginInfoEntity from PluginManiFest.
         *
         * This is the single source of truth for mapping
         * manifest data into database entity.
         */
        fun fromManifest(
            manifest: PluginManifestLocal
        ): PluginInfoEntity =
            PluginInfoEntity(
                pluginID = manifest.pluginID,

                // Basic Infos
                installedVersion = manifest.installedVersion,
                pluginRenderingName = manifest.pluginRenderingName,
                pluginPackageName = manifest.pluginPackageName,
                iconURI = manifest.iconURI,
                author = manifest.author,
                pluginDescription = manifest.pluginDescription,

                // Runtime Infos
                enabled = manifest.enabled,
                requiredEnvironment = manifest.requiredEnvironment,
                state = manifest.state,
                source = manifest.source
            )
    }
}