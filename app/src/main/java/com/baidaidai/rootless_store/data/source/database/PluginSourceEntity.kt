package com.baidaidai.rootless_store.data.source.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pluginSource")
data class PluginSourceEntity(
    @PrimaryKey
    val sourceID: String,
    val sourceName: String,
    val sourceURI: String
)