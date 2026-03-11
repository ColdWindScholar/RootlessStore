package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.execute.repository.PluginExecuteRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import javax.inject.Inject

class AbortPluginProcessUseCase @Inject constructor(
    private val pluginExecuteRepositoryImpl: PluginExecuteRepositoryImpl
) {
    suspend operator fun invoke(pluginManifestRoom: PluginManifestRoom) = pluginExecuteRepositoryImpl.abortPluginProcess(pluginManifestRoom)
}