package com.baidaidai.rootless_store.domain.source.repository

import androidx.room.RoomDatabase
import com.baidaidai.rootless_store.data.source.database.PluginSourceEntity
import com.baidaidai.rootless_store.domain.source.model.PluginSourceLocal
import com.baidaidai.rootless_store.domain.source.model.PluginSourceUser
import kotlinx.coroutines.flow.Flow

interface PluginSourceRepository {
    // 以DB为中心的Gateway

    val appDatabase: RoomDatabase

    // Create
    suspend fun insertOnePluginSource(pluginSourceUser: PluginSourceUser)

    // Update
    suspend fun updateOnePluginSource(
        sourceID: String,
        sourceName: String,
        sourceURI: String
    )

    // Read
    suspend fun getOnePluginSource(sourceID: String): PluginSourceEntity?

    fun getAllPluginSources(): Flow<List<PluginSourceLocal>?>

    // Delete
    suspend fun deleteOnePluginSource(pluginSourceEntity: PluginSourceEntity)
}
