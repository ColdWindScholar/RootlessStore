package com.baidaidai.rootless_store.domain.plugin.gateway

import androidx.room.RoomDatabase
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.model.PluginManifestLocal

interface PluginInfoGateway {
    // 以为DB为中心的Gateway

    val appDatabase: RoomDatabase
    suspend fun getOnePluginInfo(pluginID: String): PluginManifestLocal?

    suspend fun getWholePluginInfo(): List<PluginManifestLocal>?

    suspend fun insertOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    suspend fun deleteOnePluginInfo()

}