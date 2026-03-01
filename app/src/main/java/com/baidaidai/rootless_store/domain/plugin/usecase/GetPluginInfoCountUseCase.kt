package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPluginInfoCountUseCase @Inject constructor(
    private val pluginCoreRepositoryImpl: PluginCoreRepositoryImpl
) {
    operator fun invoke(): Flow<Int>{
        return pluginCoreRepositoryImpl.getPluginInfoCount()
    }
}