package com.baidaidai.rootless_store.domain.market.gateway

import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.remote.dto.PluginItemDto
import kotlinx.coroutines.flow.Flow

interface PluginMarketRepositoryGateway {
    fun getPlugins(): Flow<PagingData<PluginItemDto>>
}