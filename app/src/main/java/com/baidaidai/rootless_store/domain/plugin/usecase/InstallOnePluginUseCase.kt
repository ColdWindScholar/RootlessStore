package com.baidaidai.rootless_store.domain.plugin.usecase

import android.net.Uri
import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.error.PluginError
import javax.inject.Inject

class InstallOnePluginUseCase @Inject constructor(
    private val pluginCoreRepositoryImpl: PluginCoreRepositoryImpl
){
    suspend operator fun invoke(uri: Uri): PluginError? = pluginCoreRepositoryImpl.installOnePlugin(uri)
}