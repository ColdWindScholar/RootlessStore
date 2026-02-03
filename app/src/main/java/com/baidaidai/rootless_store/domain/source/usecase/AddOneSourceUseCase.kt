package com.baidaidai.rootless_store.domain.source.usecase

import com.baidaidai.rootless_store.data.source.repository.PluginSourceRepositoryImpl
import com.baidaidai.rootless_store.domain.source.model.PluginSourceUser
import javax.inject.Inject

class AddOneSourceUseCase @Inject constructor(
    private val pluginSourceRepositoryImpl: PluginSourceRepositoryImpl
) {
    suspend operator fun invoke(
        sourceURI: String
    ){
        val pluginSourceUser = PluginSourceUser(sourceURI = sourceURI)
        pluginSourceRepositoryImpl.insertOnePluginSource(pluginSourceUser)
    }
}