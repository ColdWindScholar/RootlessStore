package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWholePluginInfoUseCase @Inject constructor(
    private val repositoryImpl: PluginCoreRepositoryImpl
) {
    operator fun invoke(): Flow<List<PluginManifestRoom>> {
        val result = repositoryImpl.getWholePluginInfo()

        return result.map { manifests ->
            manifests.orEmpty()
        }
    }
}