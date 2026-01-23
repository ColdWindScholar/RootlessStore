package com.baidaidai.rootless_store.domain.Repository.UseCase

import com.baidaidai.rootless_store.data.repository.RepositoryImpl
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest
import javax.inject.Inject

class GetWholePluginInfoUseCase @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) {
    suspend operator fun invoke(): List<PluginManiFest> {
        val result = repositoryImpl.getWholePluginInfo()

        if (result.isNullOrEmpty()){
            return listOf(PluginManiFest._testOnly_)
        }else{
            return result
        }
    }
}