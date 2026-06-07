package com.baidaidai.rootless_store.data.plugin.repository

import android.net.Uri
import android.util.Log
import com.baidaidai.rootless_store.core.util.OutOfStringLike
import com.baidaidai.rootless_store.data.database.RootlessStoreDatabase
import com.baidaidai.rootless_store.data.plugin.gateway.PluginCoreGatewayImpl
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.domain.plugin.error.PluginError
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifest
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import com.baidaidai.rootless_store.domain.plugin.manifest.RootlessStoreManifestCollection
import com.baidaidai.rootless_store.domain.plugin.model.LocalManifest
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal

import com.baidaidai.rootless_store.domain.plugin.repository.PluginCoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import kotlin.collections.joinToString

class PluginCoreRepositoryImpl @Inject constructor(
    rootlessStoreDatabase: RootlessStoreDatabase,
    private val pluginCoreGatewayImpl: PluginCoreGatewayImpl
): PluginCoreRepository {

    private val pluginInfoDAO = rootlessStoreDatabase.pluginInfoDao()
    private val environmentInfoDAO = rootlessStoreDatabase.environmentInfoDao()

    // Create
    override suspend fun insertOnePluginInfo(
        pluginInfoEntity: PluginInfoEntity
    ){
        pluginInfoDAO.insertOnePluginInfo(pluginInfoEntity)
    }
    override suspend fun insertOneEnvironmentInfo(
        environmentInfoEntity: PluginInfoEntity
    ){
        environmentInfoDAO.insertOneEnvironmentInfo(environmentInfoEntity)
    }

    // Update
    override suspend fun enablePluginByID(pluginID: String) {
        pluginInfoDAO.updateEnabled(pluginID = pluginID, enabled = true)
    }

    override suspend fun disablePluginByID(pluginID: String) {
        pluginInfoDAO.updateEnabled(pluginID = pluginID, enabled = false)
    }

    override suspend fun disableAllPlugin() {
        pluginInfoDAO.disableAllPlugin()
    }

    override suspend fun enableEnvironmentByID(environmentID: String) {
        environmentInfoDAO.updateEnabled(environmentID = environmentID, enabled = true)
    }

    override suspend fun disableEnvironmentByID(environmentID: String) {
        environmentInfoDAO.updateEnabled(environmentID = environmentID, enabled = false)
    }

    // READ
    override suspend fun getOnePluginInfo(
        pluginID: String
    ): PluginManifestRoom? {
        val pluginInfo = pluginInfoDAO.getOneEntirePluginInfoByPluginID(pluginID)
        return pluginInfo
    }

    override suspend fun getOneEnvironmentInfo(
        environmentID: String
    ): PluginManifestLocal? {
        val environmentInfo = environmentInfoDAO.getOneEntireEnvironmentInfoByEnvironmentID(environmentID)
        return environmentInfo
    }

    override fun getWholePluginInfo(): Flow<List<PluginManifestRoom>> {
        val pluginManifestRoomList = pluginInfoDAO.getEntirePluginManifest()

        return pluginManifestRoomList
    }

    override fun getWholeEnvironmentInfo(): Flow<List<PluginManifestRoom>> {
        val environmentManifestList = environmentInfoDAO.getEntireEnvironmentManifest()
        return environmentManifestList
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

    override suspend fun getAvailableEnvironmentPath(): String {
        return environmentInfoDAO.getEnabledEnvironment()
            .first()
            .joinToString(":") { environmentManifest ->
                pluginCoreGatewayImpl.getEnvironmentRuntimePATH(environmentManifest)
            }
    }

    override suspend fun getAvailableEnvironmentLDPATH(): String {
        return environmentInfoDAO.getEnabledEnvironment()
            .first()
            .joinToString(":") { environmentManifest ->
                pluginCoreGatewayImpl.getEnvironmentLDPATH(environmentManifest)
            }
    }

    override suspend fun getAvailableEnvironmentConfig(): Map<String, String> {
        val environmentManifests = environmentInfoDAO.getEnabledEnvironment().first()

        return buildMap {
            environmentManifests.forEach { environmentManifest ->
                environmentManifest.env.let { putAll(environmentManifest.env!!)}
            }
        }
    }

    suspend fun getEnvironmentConfigKeyList(): List<String> {
        return getAvailableEnvironmentConfig().keys.toList()
    }

    suspend fun getEnvironmentConfigValueList(): List<String> {
        return getAvailableEnvironmentConfig().values.toList()
    }



    // Delete
    override suspend fun deleteOnePluginInfo(pluginInfoEntity: PluginInfoEntity) {
        pluginInfoDAO.deleteOnePluginInfo(pluginInfoEntity)
    }

    suspend fun deleteOneEnvironmentInfo(environmentEntity: PluginInfoEntity) {
        environmentInfoDAO.deleteOneEnvironmentInfo(environmentEntity)
    }

    // Operator
    override suspend fun installOnePlugin(
        uri: Uri,
    ): PluginError?{
        try {
            when(val pluginType = pluginCoreGatewayImpl.judgeManifest(uri)){
                LocalManifest.PluginManifestLocal -> {

                    Log.d("PluginCoreRepositoryImpl.installOnePlugin","pluginType: ${pluginType.name}")

                    val pluginManiFestRoom = pluginCoreGatewayImpl.parsePluginManifest(uri).toManifestRoom()
                    val pluginInfoEntity = PluginInfoEntity.fromPluginManifestRoom(pluginManiFestRoom)

                    pluginCoreGatewayImpl.installPluginFromLocal(uri)
                    pluginCoreGatewayImpl.setPluginEntryPointExecutable(pluginManiFestRoom)
                    insertOnePluginInfo(pluginInfoEntity)

                    return null
                }

                LocalManifest.EnvironmentManifestLocal -> {

                    Log.d("PluginCoreRepositoryImpl.installOnePlugin","pluginType: ${pluginType.name}")

                    val environmentManiFest = pluginCoreGatewayImpl.parsePluginManifest(uri).toManifestRoom()
                    val environmentEntity = PluginInfoEntity.fromPluginManifestRoom(environmentManiFest)

                    pluginCoreGatewayImpl.installEnvironmentFromLocal(uri)
                    pluginCoreGatewayImpl.setEnvironmentEntryPointExecutable(environmentManiFest)
                    insertOneEnvironmentInfo(environmentEntity)

                    return null
                }
            }
        }catch (error: Throwable){
            val errorStack  = error.stackTrace.OutOfStringLike()

            return PluginError(
                errorMessage = error.message!!,
                errorCause = errorStack
            )
        }
    }

    override suspend fun installOnePluginFromMarket(
        pluginURI: String,
        manifest: RootlessStoreManifestCollection
    ): PluginError? {
        try {
            when(manifest){
                is PluginManifest -> {
                    val pluginManifestRemote = manifest as PluginManifestRemote
                    val pluginManifestRoom = pluginManifestRemote.toManifestRoom()

                    val pluginInfoEntity = PluginInfoEntity.fromPluginManifestRoom(pluginManifestRoom)
                    if (pluginManifestRoom.pluginType == 1){
                        pluginCoreGatewayImpl.installEnvironmentFromMarket(pluginURI, pluginManifestRemote)
                        pluginCoreGatewayImpl.setEnvironmentEntryPointExecutable(environmentManifestRoom = pluginManifestRemote.toManifestRoom())
                        insertOneEnvironmentInfo(environmentInfoEntity = pluginInfoEntity)
                    }
                    pluginCoreGatewayImpl.installPluginFromMarket(pluginURI,pluginManifestRemote)
                    pluginCoreGatewayImpl.setPluginEntryPointExecutable(pluginManifestRoom = pluginManifestRemote.toManifestRoom())
                    insertOnePluginInfo(pluginInfoEntity = pluginInfoEntity)

                    return null
                }
            }
        }catch (error: Throwable){
            val errorStack  = error.stackTrace.OutOfStringLike()

            return PluginError(
                errorMessage = error.message!!,
                errorCause = errorStack
            )
        }
    }

}
