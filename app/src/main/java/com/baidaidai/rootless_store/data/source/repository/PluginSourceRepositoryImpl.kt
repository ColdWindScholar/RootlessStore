package com.baidaidai.rootless_store.data.source.repository

import android.content.Context
import com.baidaidai.rootless_store.data.database.RootlessStoreDatabase
import com.baidaidai.rootless_store.data.source.database.PluginSourceEntity
import com.baidaidai.rootless_store.data.source.gateway.PluginSourceGatewayImpl
import com.baidaidai.rootless_store.domain.source.model.PluginSourceLocal
import com.baidaidai.rootless_store.domain.source.model.PluginSourceUser
import com.baidaidai.rootless_store.domain.source.repository.PluginSourceRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PluginSourceRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    rootlessStoreDatabase: RootlessStoreDatabase,
    val pluginSourceGatewayImpl: PluginSourceGatewayImpl
): PluginSourceRepository {

    override val appDatabase = rootlessStoreDatabase

    private val pluginSourceDAO = appDatabase.pluginSourceDao()

    // Create
    override suspend fun insertOnePluginSource(
        pluginSourceUser: PluginSourceUser
    ) {
        val pluginSourceDTO = pluginSourceGatewayImpl.getPluginSourceMetaInfo(pluginSourceUser.sourceURI)
        val newPluginSourceEntity = PluginSourceEntity.fromPluginSourceDTO(pluginSourceDTO)

        pluginSourceDAO.insertOnePluginSource(newPluginSourceEntity)
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

    override fun getAllPluginSources(): Flow<List<PluginSourceLocal>?> {
        return pluginSourceDAO.getAllPluginSources()
    }

    // Delete
    override suspend fun deleteOnePluginSource(
        pluginSourceEntity: PluginSourceEntity
    ) {
        pluginSourceDAO.deleteOnePluginSource(pluginSourceEntity)
    }

}
