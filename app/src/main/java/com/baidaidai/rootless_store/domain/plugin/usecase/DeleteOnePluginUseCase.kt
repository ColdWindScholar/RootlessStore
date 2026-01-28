package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.plugin.repository.PluginInfoRepositoryImpl
import javax.inject.Inject

class DeleteOnePluginUseCase @Inject constructor(
    private val repositoryImpl: PluginInfoRepositoryImpl,

    ) {
    operator fun invoke(){

    }
}