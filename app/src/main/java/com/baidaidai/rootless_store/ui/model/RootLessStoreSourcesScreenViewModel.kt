package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.domain.source.model.PluginSourceLocal
import com.baidaidai.rootless_store.domain.source.usecase.AddOneSourceUseCase
import com.baidaidai.rootless_store.domain.source.usecase.GetWholeSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootLessStoreSourcesScreenViewModel @Inject constructor(
    getWholeSourceUseCase: GetWholeSourceUseCase,
    private val addOneSourceUseCase: AddOneSourceUseCase
): ViewModel(){

    val sourceList = getWholeSourceUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList<PluginSource>()
    )
}