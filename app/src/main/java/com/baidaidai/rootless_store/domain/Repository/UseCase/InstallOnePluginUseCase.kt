package com.baidaidai.rootless_store.domain.Repository.UseCase

import android.net.Uri
import androidx.hilt.navigation.compose.hiltViewModel
import com.baidaidai.rootless_store.data.local.room.PluginInfoDAO
import com.baidaidai.rootless_store.data.local.room.PluginInfoEntity
import com.baidaidai.rootless_store.data.pluginFileSystem.impl.PluginFileSystemGatewayImpl
import com.baidaidai.rootless_store.data.repository.RepositoryImpl
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest
import com.baidaidai.rootless_store.ui.model.RootLessStorePluginScreenViewModel
import javax.inject.Inject

class InstallOnePluginUseCase @Inject constructor(
    private val pluginFileSystemGatewayImpl: PluginFileSystemGatewayImpl,
    private val repositoryImpl: RepositoryImpl
){
    suspend operator fun invoke(
        uri: Uri,
    ){
        /**
         * 1. Install Plugin
         * 2. Inset DB Content
         * 3. Update VM
         */
        // n
        val pluginManiFest = pluginFileSystemGatewayImpl.readPluginManifest(uri)
        val pluginInfoEntity = PluginInfoEntity.fromManifest(pluginManiFest)

        pluginFileSystemGatewayImpl.installPlugin(uri)
        repositoryImpl.insertOnePluginInfo(pluginInfoEntity)


    }
}