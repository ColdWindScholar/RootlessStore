package com.baidaidai.rootless_store.domain.market.usecase

import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.remote.dto.PluginItemDto
import com.baidaidai.rootless_store.data.market.repository.PluginMarketRepositoryGatewayImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemotePluginsUseCase @Inject constructor(
    private val repository: PluginMarketRepositoryGatewayImpl
) {
    operator fun invoke(): Flow<PagingData<PluginItemDto>> {
       return repository.getPlugins()
    }
}