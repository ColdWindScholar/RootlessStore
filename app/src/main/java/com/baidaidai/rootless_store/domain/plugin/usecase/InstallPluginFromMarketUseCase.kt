package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import javax.inject.Inject

class InstallPluginFromMarketUseCase @Inject constructor(
    private val pluginCoreRepositoryImpl: PluginCoreRepositoryImpl
) {
    suspend operator fun invoke(
        pluginURI: String,
        pluginManifestRemote: PluginManifestRemote
    ) = pluginCoreRepositoryImpl.installOnePluginFromMarket(pluginURI,pluginManifestRemote)
}