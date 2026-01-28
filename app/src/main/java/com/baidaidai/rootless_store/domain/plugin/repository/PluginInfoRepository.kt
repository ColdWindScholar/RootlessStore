package com.baidaidai.rootless_store.domain.plugin.repository

import androidx.room.RoomDatabase
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom

interface PluginInfoRepository {
    // 以为DB为中心的Gateway

    val appDatabase: RoomDatabase
    suspend fun getOnePluginInfo(pluginID: String): PluginManifestLocal?

    suspend fun getWholePluginInfo(): List<PluginManifestRoom>?

    suspend fun insertOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    suspend fun deleteOnePluginInfo()

    suspend fun enablePluginByID(pluginID: String)

    suspend fun disablePluginByID(pluginID: String)

}