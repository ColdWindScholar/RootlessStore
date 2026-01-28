package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.model.PluginManifestLocal
import javax.inject.Inject

class GetWholePluginInfoUseCase @Inject constructor(
    private val repositoryImpl: PluginInfoRepositoryImpl
) {
    suspend operator fun invoke(): List<PluginManifestLocal> {
        val result = repositoryImpl.getWholePluginInfo()

        if (result.isNullOrEmpty()){
            return listOf(PluginManifestLocal._testOnly_)
        }else{
            return result
        }
    }
}