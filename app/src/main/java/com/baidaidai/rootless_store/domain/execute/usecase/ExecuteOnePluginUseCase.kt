package com.baidaidai.rootless_store.domain.execute.usecase

import com.baidaidai.rootless_store.data.execute.repository.PluginExecuteRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExecuteOnePluginUseCase @Inject constructor(
    private val pluginExecuteRepositoryImpl: PluginExecuteRepositoryImpl
) {
    operator fun invoke(pluginManifestRoom: PluginManifestRoom): Flow<String> = pluginExecuteRepositoryImpl.executeOnePlugin(pluginManifestRoom)
}