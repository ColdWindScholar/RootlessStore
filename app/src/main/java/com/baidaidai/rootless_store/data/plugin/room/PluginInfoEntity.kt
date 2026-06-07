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
    val Type: Int,
    val ID: String,

    // Plugin Basic Infos
    val Version: String,
    val RenderingName: String,
    val PackageName: String,
    val iconURI: String?,
    val author: String,
    val Description:String,

    // Plugin Runtime Infos
    val enabled: Boolean,
    val Dependences: HosterOverallStatus,
    val state: PluginState,
    val source: PluginSource,
    val entryPoint: String,
    val env: Map<String, String>?,
    val ldLibraryPath: List<String>?,
){
    companion object {

        /**
         * Create a PluginInfoEntity from PluginManiFest.
         *
         * This is the single source of truth for mapping
         * manifest data into database entity.
         */
        fun fromPluginManifestRoom(
            pluginManifestRoom: PluginManifestRoom
        ): PluginInfoEntity =
            PluginInfoEntity(
                Type = pluginManifestRoom.Type,
                ID = pluginManifestRoom.ID,

                // Basic Infos
                Version = pluginManifestRoom.Version,
                RenderingName = pluginManifestRoom.RenderingName,
                PackageName = pluginManifestRoom.PackageName,
                iconURI = pluginManifestRoom.iconURI,
                author = pluginManifestRoom.author,
                Description = pluginManifestRoom.Description,

                // Runtime Infos
                enabled = false,
                Dependences = pluginManifestRoom.Dependences,
                state = pluginManifestRoom.state,
                source = pluginManifestRoom.source,
                entryPoint = pluginManifestRoom.entryPoint,
                env = pluginManifestRoom.env,
                ldLibraryPath = pluginManifestRoom.ldLibraryPath
            )
    }
}