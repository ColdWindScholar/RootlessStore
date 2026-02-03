package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.baidaidai.rootless_store.domain.market.usecase.GetRemotePluginsUseCase
import com.baidaidai.rootless_store.domain.source.usecase.GetWholeSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootLessStoreSourcesScreenViewModel @Inject constructor(
    getRemotePluginsUseCase: GetRemotePluginsUseCase,
    getWholeSourceUseCase: GetWholeSourceUseCase
): ViewModel(){
    val plugins = getRemotePluginsUseCase().cachedIn(viewModelScope)
}