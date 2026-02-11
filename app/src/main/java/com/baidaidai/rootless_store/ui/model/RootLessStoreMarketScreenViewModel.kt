package com.baidaidai.rootless_store.ui.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.baidaidai.rootless_store.domain.market.usecase.GetRemotePluginListUseCase
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRemote
import com.baidaidai.rootless_store.domain.plugin.usecase.InstallPluginFromMarketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootLessStoreMarketScreenViewModel @Inject constructor(
    private val getRemotePluginListUseCase: GetRemotePluginListUseCase,
    private val installPluginFromMarketUseCase: InstallPluginFromMarketUseCase
): ViewModel() {
    private var _pluginSourceUri = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val remotePluginList = _pluginSourceUri
        .filterNotNull()
        .flatMapLatest { uri ->
            Log.d("null1",uri)
            getRemotePluginListUseCase(pluginSourceUri = uri)
        }
        // cachedIn 一般放最后，缓存 PagingData 以及其上游变换结果
        .cachedIn(viewModelScope)

    fun updatePluginSourceUri(
        pluginSourceUri: String
    ){
        _pluginSourceUri.update { old ->
            if (pluginSourceUri != old){
                Log.d("null2",pluginSourceUri)
                pluginSourceUri
            }else{
                old
            }
        }
    }

    fun installPlugin(
        pluginURI: String,
        pluginManifestRemote: PluginManifestRemote
    ){
        viewModelScope.launch {
            installPluginFromMarketUseCase(pluginURI,pluginManifestRemote)
        }
    }

}