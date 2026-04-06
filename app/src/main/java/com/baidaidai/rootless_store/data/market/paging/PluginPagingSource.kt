package com.baidaidai.rootless_store.data.market.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.baidaidai.rootless_store.core.util.OutOfStringLike
import com.baidaidai.rootless_store.data.market.remote.api.PluginMarketAPI
import com.baidaidai.rootless_store.data.market.remote.dto.PluginPageResponseDTO
import com.baidaidai.rootless_store.domain.market.error.MarketError
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import io.ktor.client.call.body

class PluginPagingSource (
    private val api: PluginMarketAPI,
    private val pluginSourceUri: String,
    private val onError: suspend (MarketError)-> Unit
) : PagingSource<Int, PluginManifestRemote>() {

    // Core Suspend Method for Paging fetching Data
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PluginManifestRemote> {
        try{
            val page = params.key ?: 0

            val resp = api.getPlugins(
                pageNumber = page,
                pluginSourceUri
            )
                .body<PluginPageResponseDTO>()

            val nextKey = if (resp.meta.hasMore) page + 1 else null

            return LoadResult.Page(
                data = resp.data,
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextKey
            )
        }catch (error: Throwable){
            onError(
                MarketError(
                    errorMessage = error.message ?: "Load plugin list failed",
                    errorCause = error.stackTrace.OutOfStringLike()
                )
            )
            return LoadResult.Error(error)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PluginManifestRemote>): Int? {
        TODO("Not yet implemented")
    }
}