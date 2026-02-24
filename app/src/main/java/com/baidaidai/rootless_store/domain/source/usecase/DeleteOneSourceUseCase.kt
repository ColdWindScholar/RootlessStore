package com.baidaidai.rootless_store.domain.source.usecase

import com.baidaidai.rootless_store.data.source.database.PluginSourceEntity
import com.baidaidai.rootless_store.data.source.repository.PluginSourceRepositoryImpl
import com.baidaidai.rootless_store.domain.source.model.PluginSourceLocal
import javax.inject.Inject

class DeleteOneSourceUseCase @Inject constructor(
    val pluginSourceRepositoryImpl: PluginSourceRepositoryImpl
) {
    suspend operator fun invoke(
        pluginSourceLocal: PluginSourceLocal
    ){
        val pluginSourceEntity = PluginSourceEntity.fromPluginSourceLocal(pluginSourceLocal)
        pluginSourceRepositoryImpl.deleteOnePluginSource(pluginSourceEntity)
    }
}