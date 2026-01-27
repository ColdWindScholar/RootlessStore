package com.baidaidai.rootless_store.domain.plugin.usecase

import com.baidaidai.rootless_store.data.repository.PluginInfoGatewayImpl
import javax.inject.Inject

class DeleteOnePluginUseCase @Inject constructor(
    private val repositoryImpl: PluginInfoGatewayImpl,

) {
    operator fun invoke(){

    }
}