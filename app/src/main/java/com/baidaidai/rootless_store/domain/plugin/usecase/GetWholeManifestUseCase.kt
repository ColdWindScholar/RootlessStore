package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWholePluginInfoUseCase @Inject constructor(
    private val repositoryImpl: PluginInfoRepositoryImpl
) {
    operator fun invoke(): Flow<List<PluginManifestRoom>> {
        val result = repositoryImpl.getWholePluginInfo()

        if (result.isNullOrEmpty()){
            return emptyList<PluginManifestRoom>()
        }else{
            return result
        }
    }
}