package com.baidaidai.rootless_store.data.market.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.paging.PluginPagingSource
import com.baidaidai.rootless_store.data.market.remote.api.PluginMarketAPI
import com.baidaidai.rootless_store.data.market.remote.dto.PluginItemDto
import com.baidaidai.rootless_store.domain.market.gateway.PluginMarketRepositoryGateway
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PluginMarketRepositoryGatewayImpl @Inject constructor(
    private val api: PluginMarketAPI
): PluginMarketRepositoryGateway {
    override fun getPlugins(): Flow<PagingData<PluginItemDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = { PluginPagingSource(api = api) }
        ).flow
    }
}