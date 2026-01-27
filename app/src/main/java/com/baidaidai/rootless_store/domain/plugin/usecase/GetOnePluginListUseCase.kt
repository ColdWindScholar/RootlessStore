package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.market.paging.PluginPagingSource
import javax.inject.Inject

class GetOnePluginListUseCase @Inject constructor(
    private val pluginPagingSource: PluginPagingSource
) {
    operator suspend fun invoke(pageNumber: Int){

    }
}