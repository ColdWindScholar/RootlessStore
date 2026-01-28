package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import javax.inject.Inject

class GetWholePluginInfoUseCase @Inject constructor(
    private val repositoryImpl: PluginInfoRepositoryImpl
) {
    suspend operator fun invoke(): List<PluginManifestRoom> {
        val result = repositoryImpl.getWholePluginInfo()

        if (result.isNullOrEmpty()){
            return emptyList<PluginManifestRoom>()
        }else{
            return result
        }
    }
}