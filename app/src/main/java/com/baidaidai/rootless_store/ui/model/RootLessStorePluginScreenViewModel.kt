package com.baidaidai.rootless_store.ui.model

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.domain.Repository.UseCase.GetWholePluginInfoUseCase
import com.baidaidai.rootless_store.domain.Repository.UseCase.InstallOnePluginUseCase
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class RootLessStorePluginScreenViewModel @Inject constructor(
    private val getWholePluginInfoUseCase: GetWholePluginInfoUseCase,
    private val installOnePluginUseCase: InstallOnePluginUseCase
): ViewModel() {

    private val _pluginInfoList = MutableStateFlow(emptyList<PluginManiFest>())
    private val _fileURI = MutableStateFlow<Uri>(value = Uri.EMPTY)
    val pluginInfoList = _pluginInfoList.asStateFlow()
    val fileURI = _fileURI.asStateFlow()

    init {
        getAllPlugins()
    }

    fun updateFileURI(uri: Uri){
        _fileURI.value = uri
    }

    fun installPlugin(){
        viewModelScope.launch {
            installOnePluginUseCase(fileURI.value)
            getAllPlugins()
        }
    }

    private fun getAllPlugins() {
        viewModelScope.launch {
            _pluginInfoList.value = getWholePluginInfoUseCase()
        }
    }


}