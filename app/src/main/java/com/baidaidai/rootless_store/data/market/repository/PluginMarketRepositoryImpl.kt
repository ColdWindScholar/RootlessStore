package com.baidaidai.rootless_store.data.market.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.paging.PluginPagingSource
import com.baidaidai.rootless_store.data.market.remote.api.PluginMarketAPI
import com.baidaidai.rootless_store.domain.market.repository.PluginMarketRepository
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PluginMarketRepositoryImpl @Inject constructor(
    private val api: PluginMarketAPI
): PluginMarketRepository {
    override fun getPlugins(
        pluginSourceUri: String
    ): Flow<PagingData<PluginManifestRemote>> {
        return Pager(
            // Rule of Paging
            config = PagingConfig(
                pageSize = 10,
            ),

            // Core & Action of Paging
            pagingSourceFactory = {
                PluginPagingSource(
                    api = api,
                    pluginSourceUri
                )
            }
        ).flow
    }
}