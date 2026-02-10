package com.baidaidai.rootless_store.domain.market.usecase

import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.repository.PluginMarketRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemotePluginListUseCase @Inject constructor(
    private val pluginMarketRepository: PluginMarketRepositoryImpl
) {
    operator fun invoke(
        pluginSourceUri: String
    ): Flow<PagingData<PluginManifestRemote>> {
       return pluginMarketRepository.getPlugins(pluginSourceUri)
    }
}