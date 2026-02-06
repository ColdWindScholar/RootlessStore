package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPluginInfoCountUseCase @Inject constructor(
    private val pluginInfoRepositoryImpl: PluginInfoRepositoryImpl
) {
    operator fun invoke(): Flow<Int>{
        return pluginInfoRepositoryImpl.getPluginInfoCount()
    }
}