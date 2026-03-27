package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.execute.repository.PluginExecuteRepositoryImpl
import com.baidaidai.rootless_store.data.status.repository.StoreStatusRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import javax.inject.Inject

class AbortPluginProcessUseCase @Inject constructor(
    private val pluginExecuteRepositoryImpl: PluginExecuteRepositoryImpl,
    private val storeStatusRepositoryImpl: StoreStatusRepositoryImpl
) {
    suspend operator fun invoke(pluginManifestRoom: PluginManifestRoom) = if (storeStatusRepositoryImpl.getOverallStatus() == HosterOverallStatus.ADB) {
        pluginExecuteRepositoryImpl.abortPluginProcessByShizuku(pluginManifestRoom)
    }else{
        pluginExecuteRepositoryImpl.abortPluginProcess(pluginManifestRoom)
    }
}