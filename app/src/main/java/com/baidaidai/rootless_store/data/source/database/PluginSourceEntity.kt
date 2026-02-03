package com.baidaidai.rootless_store.data.source.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baidaidai.rootless_store.domain.source.model.PluginSource

@Entity(tableName = "pluginSource")
data class PluginSourceEntity(
    @PrimaryKey
    val sourceID: String,
    val sourceName: String,
    val sourceURI: String
){
    companion object {
        fun fromPluginSource(
            pluginSource: PluginSource
        ): PluginSourceEntity{
            return PluginSourceEntity(
                sourceID = pluginSource.sourceID,
                sourceName = pluginSource.sourceName!!,
                sourceURI = pluginSource.sourceURI
            )
        }
    }
}