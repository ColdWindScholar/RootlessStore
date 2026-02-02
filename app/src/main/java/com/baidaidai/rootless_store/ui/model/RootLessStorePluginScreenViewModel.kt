package com.baidaidai.rootless_store.ui.model

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.domain.plugin.usecase.GetWholePluginInfoUseCase
import com.baidaidai.rootless_store.domain.plugin.usecase.InstallOnePluginUseCase
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import com.baidaidai.rootless_store.domain.plugin.usecase.SetPluginEnabledUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class RootLessStorePluginScreenViewModel @Inject constructor(
    private val getWholePluginInfoUseCase: GetWholePluginInfoUseCase,
    private val installOnePluginUseCase: InstallOnePluginUseCase,
    private val setPluginEnabledUseCase: SetPluginEnabledUseCase
): ViewModel() {

//    private val _pluginInfoList = getAllPlugins()  // Will change back to PluginManifestLocal feature
    private val _fileURI = MutableStateFlow<Uri>(value = Uri.EMPTY)
    val pluginInfoList = _pluginInfoList.asStateFlow()
    val fileURI = _fileURI.asStateFlow()

    init {
//        getAllPlugins()
    }

    fun updateFileURI(uri: Uri){
        _fileURI.value = uri
    }

    fun installPlugin(){
        viewModelScope.launch {
            installOnePluginUseCase(fileURI.value)
//            getAllPlugins()
        }
    }
    fun setPluginEnabled(
        pluginID: String,
        pluginEnabledStatus: Boolean
    ){
        viewModelScope.launch {
            setPluginEnabledUseCase(
                pluginID = pluginID,
                pluginEnabledStatus = pluginEnabledStatus
            )
        }
    }

//    private fun getAllPlugins() {
//        return viewModelScope.launch{
//            return@launch  = getWholePluginInfoUseCase().stateIn()
//        }.await()
//    }


}