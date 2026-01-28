package com.baidaidai.rootless_store.domain.market.usecase

import androidx.paging.PagingData
import com.baidaidai.rootless_store.data.market.repository.PluginMarketRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemotePluginsUseCase @Inject constructor(
    private val repository: PluginMarketRepositoryImpl
) {
    operator fun invoke(): Flow<PagingData<PluginManifestRemote>> {
       return repository.getPlugins()
    }
}