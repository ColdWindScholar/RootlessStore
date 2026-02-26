package com.baidaidai.rootless_store.domain.plugin.usecase

import android.util.Log
import com.baidaidai.rootless_store.domain.plugin.repository.PluginInfoRepository
import com.baidaidai.rootless_store.data.plugin.repository.PluginCoreRepositoryImpl
import javax.inject.Inject

class SetPluginEnabledUseCase @Inject constructor(
    private val pluginInfoRepository: PluginCoreRepositoryImpl
) {
    suspend operator fun invoke(
        pluginID: String,
        pluginEnabledStatus: Boolean
    ){
        if (pluginEnabledStatus){
            pluginInfoRepository.enablePluginByID(pluginID)
        }else{
            pluginInfoRepository.disablePluginByID(pluginID)
        }
    }
}