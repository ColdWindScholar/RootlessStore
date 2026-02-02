package com.baidaidai.rootless_store.data.plugin.repository

import android.content.Context
import com.baidaidai.rootless_store.data.database.RootlessStoreDatabase
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.repository.PluginInfoRepository
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PluginInfoRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
): PluginInfoRepository {

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
    override suspend fun enablePluginByID(pluginID: String) {
        pluginInfoDAO.updateEnabled(pluginID = pluginID, enabled = true)
    }

    override suspend fun disablePluginByID(pluginID: String) {
        pluginInfoDAO.updateEnabled(pluginID = pluginID, enabled = false)
    }

    // READ
    override suspend fun getOnePluginInfo(
        pluginID: String
    ): PluginManifestLocal? {
//      TODO("Not yet implemented")
        val pluginInfo = pluginInfoDAO.getOneEntirePluginInfoByPluginID(pluginID)
        return pluginInfo
    }

    override suspend fun getWholePluginInfo(): List<PluginManifestRoom>? {
//      TODO("Not yet implemented")
        val pluginManifestList = pluginInfoDAO.getEntirePluginManifest()
        return pluginManifestList
    }

    // Delete
    override suspend fun deleteOnePluginInfo() {
        TODO("Not yet implemented")
    }

}