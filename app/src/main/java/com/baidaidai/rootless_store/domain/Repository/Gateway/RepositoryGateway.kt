package com.baidaidai.rootless_store.domain.Repository.Gateway

import androidx.room.RoomDatabase
import com.baidaidai.rootless_store.data.local.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest

interface RepositoryGateway {

    val appDatabase: RoomDatabase
    suspend fun getOnePluginInfo(pluginID: String): PluginManiFest?

    suspend fun getWholePluginInfo(): List<PluginManiFest>?

    suspend fun insertOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    suspend fun deleteOnePluginInfo()

}