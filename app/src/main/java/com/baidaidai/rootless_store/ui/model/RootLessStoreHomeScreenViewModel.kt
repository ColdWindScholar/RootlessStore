package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.domain.status.model.RootlessStoreHosterStatus
import com.baidaidai.rootless_store.domain.status.usecase.GetRootlessStoreHosterStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RootLessStoreHomeScreenViewModel @Inject constructor(
    getRootlessStoreHosterStatusUseCase: GetRootlessStoreHosterStatusUseCase
): ViewModel() {

    val rootlessStoreHosterStatus: StateFlow<RootlessStoreHosterStatus> =
        getRootlessStoreHosterStatusUseCase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = RootlessStoreHosterStatus()
        )
}