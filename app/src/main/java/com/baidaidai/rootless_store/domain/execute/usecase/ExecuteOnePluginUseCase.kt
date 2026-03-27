package com.baidaidai.rootless_store.domain.execute.usecase

import com.baidaidai.rootless_store.data.execute.repository.PluginExecuteRepositoryImpl
import com.baidaidai.rootless_store.data.status.repository.StoreStatusRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExecuteOnePluginUseCase @Inject constructor(
    private val pluginExecuteRepositoryImpl: PluginExecuteRepositoryImpl,
    val storeStatusRepositoryImpl: StoreStatusRepositoryImpl
) {
    operator fun invoke(
        pluginManifestRoom: PluginManifestRoom
    ): Flow<String> = if (storeStatusRepositoryImpl.getOverallStatus() == HosterOverallStatus.ADB) {
            pluginExecuteRepositoryImpl.executeOnePluginByShizuku(pluginManifestRoom)
        }else{
            pluginExecuteRepositoryImpl.executeOnePlugin(pluginManifestRoom)
        }
}