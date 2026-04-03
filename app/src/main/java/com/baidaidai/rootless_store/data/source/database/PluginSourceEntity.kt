package com.baidaidai.rootless_store.data.source.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baidaidai.rootless_store.data.source.remote.dto.PluginSourceDTO
import com.baidaidai.rootless_store.domain.source.model.PluginSource
import com.baidaidai.rootless_store.domain.source.model.PluginSourceLocal

@Entity(tableName = "pluginSource")
data class PluginSourceEntity(
    @PrimaryKey
    override val sourceID: String,
    override val sourceName: String,
    override val sourceRemoteEndpoint: String
): PluginSource.PluginSourceEntity{
    companion object {

        // Based on Adding PluginSource
        fun fromPluginSourceDTO(
            pluginSourceDTO: PluginSourceDTO
        ): PluginSourceEntity{
            return PluginSourceEntity(
                sourceID = pluginSourceDTO.sourceID,
                sourceName = pluginSourceDTO.sourceName,
                sourceRemoteEndpoint = pluginSourceDTO.sourceRemoteEndpoint
            )
        }


        // Based on Delete PluginSource
        fun fromPluginSourceLocal(
            pluginSourceLocal: PluginSourceLocal
        ): PluginSourceEntity{
            return PluginSourceEntity(
                sourceID = pluginSourceLocal.sourceID,
                sourceName = pluginSourceLocal.sourceName,
                sourceRemoteEndpoint = pluginSourceLocal.sourceRemoteEndpoint
            )
        }
    }
}