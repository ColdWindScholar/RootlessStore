package com.baidaidai.rootless_store.domain.market.repository

import androidx.paging.PagingData
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import kotlinx.coroutines.flow.Flow

interface PluginMarketRepository {
    fun getPlugins(): Flow<PagingData<PluginManifestRemote>>
}