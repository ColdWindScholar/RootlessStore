package com.baidaidai.rootless_store.domain.plugin.repository

import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.error.PluginError

import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import com.baidaidai.rootless_store.domain.plugin.manifest.RootlessStoreManifestCollection
import kotlinx.coroutines.flow.Flow

interface PluginCoreRepository {
    // Create
    suspend fun insertOnePluginInfo(pluginInfoEntity: PluginInfoEntity)
    suspend fun insertOneEnvironmentInfo(environmentInfoEntity: PluginInfoEntity)

    // Read
    suspend fun getOnePluginInfo(pluginID: String): PluginManifestRoom?
    suspend fun getOneEnvironmentInfo(environmentID: String): PluginManifestLocal?
    fun getWholePluginInfo(): Flow<List<PluginManifestRoom>?>
    fun getWholeEnvironmentInfo(): Flow<List<PluginManifestRoom>?>
    fun getPluginInfoCount(): Flow<Int>
    suspend fun getTotalPluginCount(): Int
    suspend fun getEnabledPluginCount(): Int

    suspend fun getAvailableEnvironmentPath(): String
    suspend fun getAvailableEnvironmentLDPATH(): String
    suspend fun getAvailableEnvironmentConfig(): Map<String, String>

    // Update
    suspend fun enablePluginByID(pluginID: String)
    suspend fun disablePluginByID(pluginID: String)
    suspend fun disableAllPlugin()
    suspend fun enableEnvironmentByID(environmentID: String)
    suspend fun disableEnvironmentByID(environmentID: String)

    // Delete
    suspend fun deleteOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    // Operator
    suspend fun installOnePlugin(uri: Uri): PluginError?
    suspend fun installOnePluginFromMarket(pluginURI: String, manifest: RootlessStoreManifestCollection): PluginError?
}
