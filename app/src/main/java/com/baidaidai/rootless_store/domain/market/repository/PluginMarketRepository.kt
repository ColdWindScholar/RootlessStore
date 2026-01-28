package com.baidaidai.rootless_store.domain.market.repository

import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.remote.dto.PluginItemDto
import kotlinx.coroutines.flow.Flow

interface PluginMarketRepository {
    fun getPlugins(): Flow<PagingData<PluginItemDto>>
}