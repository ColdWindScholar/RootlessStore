package com.baidaidai.rootless_store.domain.Repository.UseCase

import androidx.compose.runtime.Composable
import com.baidaidai.rootless_store.data.local.room.PluginInfoDAO
import com.baidaidai.rootless_store.data.repository.RepositoryImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeleteOnePluginUseCase @Inject constructor(
    private val repositoryImpl: RepositoryImpl,

) {
    operator fun invoke(){

    }
}