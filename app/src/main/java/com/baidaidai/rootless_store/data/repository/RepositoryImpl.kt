package com.baidaidai.rootless_store.data.repository

import android.content.Context
import androidx.room.Room
import com.baidaidai.rootless_store.data.local.room.PluginInfoDataBase
import com.baidaidai.rootless_store.data.local.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.gateway.PluginInfoGateway
import com.baidaidai.rootless_store.domain.plugin.model.PluginManifestLocal
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PluginInfoGatewayImpl @Inject constructor(
    @ApplicationContext context: Context,
): PluginInfoGateway {

    override val appDatabase = Room.databaseBuilder(
        context = context,
        klass = PluginInfoDataBase::class.java,
        name = "pluginInfo.db"
    ).build()

    private val pluginInfoDAO = appDatabase.pluginInfoDao()

    // Create
    override suspend fun insertOnePluginInfo(
        pluginInfoEntity: PluginInfoEntity
    ){
        pluginInfoDAO.insertOnePluginInfo(pluginInfoEntity)
    }

    // Update

    // READ
    override suspend fun getOnePluginInfo(
        pluginID: String
    ): PluginManifestLocal? {
//      TODO("Not yet implemented")
        val pluginInfo = pluginInfoDAO.getOneEntirePluginInfoByPluginID(pluginID)
        return pluginInfo
    }

    override suspend fun getWholePluginInfo(): List<PluginManifestLocal>? {
//      TODO("Not yet implemented")
        val pluginManifestList = pluginInfoDAO.getEntirePluginManifest()
        return pluginManifestList
    }

    // Delete
    override suspend fun deleteOnePluginInfo() {
        TODO("Not yet implemented")
    }

}