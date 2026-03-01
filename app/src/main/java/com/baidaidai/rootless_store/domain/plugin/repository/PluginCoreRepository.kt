package com.baidaidai.rootless_store.domain.plugin.repository

import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.error.PluginError
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import kotlinx.coroutines.flow.Flow

interface PluginCoreRepository {
    // Create
    suspend fun insertOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    // Read
    suspend fun getOnePluginInfo(pluginID: String): PluginManifestLocal?
    fun getWholePluginInfo(): Flow<List<PluginManifestRoom>?>
    fun getPluginInfoCount(): Flow<Int>

    // Update
    suspend fun enablePluginByID(pluginID: String)
    suspend fun disablePluginByID(pluginID: String)

    // Delete
    suspend fun deleteOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    // Operator
    suspend fun installOnePlugin(uri: Uri): PluginError?
}