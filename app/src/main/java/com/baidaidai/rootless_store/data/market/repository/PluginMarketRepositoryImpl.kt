package com.baidaidai.rootless_store.data.market.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.paging.PluginPagingSource
import com.baidaidai.rootless_store.data.market.remote.api.PluginMarketAPI
import com.baidaidai.rootless_store.data.market.remote.dto.PluginItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PluginMarketRepositoryImpl @Inject constructor(
    private val api: PluginMarketAPI
) {
    fun getPlugins(pageNumber: Int): Flow<PagingData<PluginItemDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = { PluginPagingSource(api = api) }
        ).flow
    }
}