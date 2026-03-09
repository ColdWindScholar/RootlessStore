package com.baidaidai.rootless_store.data.plugin.repository

import android.net.Uri
import com.baidaidai.rootless_store.core.util.OutOfStringLike
import com.baidaidai.rootless_store.data.database.RootlessStoreDatabase
import com.baidaidai.rootless_store.data.plugin.gateway.PluginCoreGatewayImpl
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.error.PluginError
import com.baidaidai.rootless_store.domain.plugin.repository.PluginCoreRepository
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PluginCoreRepositoryImpl @Inject constructor(
    rootlessStoreDatabase: RootlessStoreDatabase,
    private val pluginCoreGatewayImpl: PluginCoreGatewayImpl
): PluginCoreRepository {

    private val pluginInfoDAO = rootlessStoreDatabase.pluginInfoDao()

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
        val pluginInfo = pluginInfoDAO.getOneEntirePluginInfoByPluginID(pluginID)
        return pluginInfo
    }

    override fun getWholePluginInfo(): Flow<List<PluginManifestRoom>?> {
        val pluginManifestList = pluginInfoDAO.getEntirePluginManifest()
        return pluginManifestList
    }

    override fun getPluginInfoCount(): Flow<Int> {
        return pluginInfoDAO.getPluginInfoCount()
    }

    override suspend fun getTotalPluginCount(): Int {
        return pluginInfoDAO.getTotalPluginCount()
    }

    override suspend fun getEnabledPluginCount(): Int {
        return pluginInfoDAO.getEnabledPluginCount()
    }

    // Delete
    override suspend fun deleteOnePluginInfo(pluginInfoEntity: PluginInfoEntity) {
        pluginInfoDAO.deleteOnePluginInfo(pluginInfoEntity)
    }

    // Operator
    override suspend fun installOnePlugin(
        uri: Uri,
    ): PluginError?{
        try {
            val pluginManiFest = pluginCoreGatewayImpl.parsePluginManifest(uri).toManifestRoom()
            val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManiFest)

            pluginCoreGatewayImpl.installPluginFromLocal(uri)
            insertOnePluginInfo(pluginInfoEntity)

            return null
        }catch (error: Throwable){
            val errorStack  = error.stackTrace.OutOfStringLike()

            return PluginError(
                errorMessage = error.message!!,
                errorCause = errorStack
            )
        }
    }

}