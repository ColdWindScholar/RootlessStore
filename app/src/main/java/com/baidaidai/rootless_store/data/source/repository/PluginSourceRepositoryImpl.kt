package com.baidaidai.rootless_store.data.source.repository

import android.content.Context
import com.baidaidai.rootless_store.data.database.RootlessStoreDatabase
import com.baidaidai.rootless_store.data.source.database.PluginSourceEntity
import com.baidaidai.rootless_store.data.source.remote.api.PluginSourceAPI
import com.baidaidai.rootless_store.domain.source.model.PluginSource
import com.baidaidai.rootless_store.domain.source.repository.PluginSourceRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PluginSourceRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    rootlessStoreDatabase: RootlessStoreDatabase,
    val pluginSourceAPI: PluginSourceAPI
): PluginSourceRepository {

    override val appDatabase = rootlessStoreDatabase

    private val pluginSourceDAO = appDatabase.pluginSourceDao()

    // Create
    override suspend fun insertOnePluginSource(
        pluginSource: PluginSource
    ) {
        // Check
        if (pluginSource.sourceName.isNullOrEmpty()){
            // fetch sourceName
            val httpResponse = pluginSourceAPI.getPluginSourceMetaInfo(
                pluginSourceURI = pluginSource.sourceURI
            )
            val httpResponseBody = httpResponse.body<PluginSource>()  // change to DTO future

            val pluginSource = pluginSource.copy(sourceName = httpResponseBody.sourceName)
            val pluginSourceEntity = PluginSourceEntity.fromPluginSource(pluginSource)

            pluginSourceDAO.insertOnePluginSource(pluginSourceEntity)
        }else{
            val pluginSourceEntity = PluginSourceEntity.fromPluginSource(pluginSource)
            pluginSourceDAO.insertOnePluginSource(pluginSourceEntity)
        }
    }

    // Update
    override suspend fun updateOnePluginSource(
        sourceID: String,
        sourceName: String,
        sourceURI: String
    ) {
        pluginSourceDAO.updateOnePluginSource(
            sourceID = sourceID,
            sourceName = sourceName,
            sourceURI = sourceURI
        )
    }

    // Read
    override suspend fun getOnePluginSource(
        sourceID: String
    ): PluginSourceEntity? {
        return pluginSourceDAO.getOnePluginSourceBySourceID(sourceID)
    }

    override fun getAllPluginSources(): Flow<List<PluginSource>?> {
        return pluginSourceDAO.getAllPluginSources()
    }

    // Delete
    override suspend fun deleteOnePluginSource(
        pluginSourceEntity: PluginSourceEntity
    ) {
        pluginSourceDAO.deleteOnePluginSource(pluginSourceEntity)
    }

}
