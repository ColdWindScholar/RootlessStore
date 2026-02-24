package com.baidaidai.rootless_store.data.source.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baidaidai.rootless_store.data.source.remote.dto.PluginSourceDTO
import com.baidaidai.rootless_store.domain.source.model.PluginSourceLocal

@Entity(tableName = "pluginSource")
data class PluginSourceEntity(
    @PrimaryKey
    val sourceID: String,
    val sourceName: String,
    val sourceURI: String
){
    companion object {
        fun fromPluginSourceDTO(
            pluginSourceDTO: PluginSourceDTO
        ): PluginSourceEntity{
            return PluginSourceEntity(
                sourceID = pluginSourceDTO.sourceID,
                sourceName = pluginSourceDTO.sourceName,
                sourceURI = pluginSourceDTO.sourceURI
            )
        }

        fun fromPluginSourceLocal(
            pluginSourceLocal: PluginSourceLocal
        ): PluginSourceEntity{
            return PluginSourceEntity(
                sourceID = pluginSourceLocal.sourceID,
                sourceName = pluginSourceLocal.sourceName,
                sourceURI = pluginSourceLocal.sourceURI
            )
        }
    }
}