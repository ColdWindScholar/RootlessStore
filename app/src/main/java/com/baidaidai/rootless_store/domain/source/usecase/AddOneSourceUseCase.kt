package com.baidaidai.rootless_store.domain.source.usecase

import com.baidaidai.rootless_store.data.source.repository.PluginSourceRepositoryImpl
import com.baidaidai.rootless_store.domain.source.model.PluginSource

class AddOneSourceUseCase(
    private val pluginSourceRepositoryImpl: PluginSourceRepositoryImpl
) {
    suspend operator fun invoke(
        pluginSource: PluginSource
    ){
        pluginSourceRepositoryImpl.insertOnePluginSource(pluginSource)
    }
}