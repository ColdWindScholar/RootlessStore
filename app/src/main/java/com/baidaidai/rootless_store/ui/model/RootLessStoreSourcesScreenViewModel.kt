package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.baidaidai.rootless_store.data.market.repository.PluginMarketRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootLessStoreSourcesScreenViewModel @Inject constructor(
    repository: PluginMarketRepositoryImpl
): ViewModel(){
    val plugins = repository.getPlugins(1).cachedIn(viewModelScope)
}