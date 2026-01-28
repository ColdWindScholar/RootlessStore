package com.baidaidai.rootless_store.data.market.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.baidaidai.rootless_store.data.market.remote.api.PluginMarketAPI
import com.baidaidai.rootless_store.data.market.remote.dto.PluginItemDto
import com.baidaidai.rootless_store.data.market.remote.dto.PluginPageResponseDto
import io.ktor.client.call.body

class PluginPagingSource (
    private val api: PluginMarketAPI
) : PagingSource<Int, PluginItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PluginItemDto> {
        val page = params.key ?: 0

        val resp = api.getPlugins(pageNumber = page).body<PluginPageResponseDto>()

        val nextKey = if (resp.meta.hasMore) page + 1 else null

        return LoadResult.Page(
            data = resp.data,
            prevKey = if (page == 0) null else page - 1,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PluginItemDto>): Int? {
        TODO("Not yet implemented")
    }
}