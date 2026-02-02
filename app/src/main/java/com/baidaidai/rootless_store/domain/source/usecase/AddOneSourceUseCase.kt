package com.baidaidai.rootless_store.domain.source.usecase

import com.baidaidai.rootless_store.data.source.database.PluginSourceEntity
import com.baidaidai.rootless_store.data.source.repository.PluginSourceRepositoryImpl

class AddOneSourceUseCase(
    private val pluginSourceRepositoryImpl: PluginSourceRepositoryImpl
) {
    suspend operator fun invoke(
        pluginSourceEntity: PluginSourceEntity
    ){
        pluginSourceRepositoryImpl.insertOnePluginSource(pluginSourceEntity)
    }
}