package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.baidaidai.rootless_store.domain.market.usecase.GetRemotePluginsUseCase
import com.baidaidai.rootless_store.domain.plugin.model.PluginSource
import com.baidaidai.rootless_store.domain.source.usecase.GetWholeSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RootLessStoreSourcesScreenViewModel @Inject constructor(
    getRemotePluginsUseCase: GetRemotePluginsUseCase,
    getWholeSourceUseCase: GetWholeSourceUseCase
): ViewModel(){
    val plugins = getRemotePluginsUseCase().cachedIn(viewModelScope)
    val sourceList = getWholeSourceUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList<PluginSource>()
    )
}