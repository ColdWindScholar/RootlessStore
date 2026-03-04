package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.domain.status.model.MemoryStatus
import com.baidaidai.rootless_store.domain.status.model.StorageStatus
import com.baidaidai.rootless_store.domain.status.usecase.GetMemoryStatusUseCase
import com.baidaidai.rootless_store.domain.status.usecase.GetStorageStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RootLessStoreHomeScreenViewModel @Inject constructor(
    getStorageStatusUseCase: GetStorageStatusUseCase,
    getMemoryStatusUseCase: GetMemoryStatusUseCase
): ViewModel() {

    val storageStatus: StateFlow<StorageStatus> = getStorageStatusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = StorageStatus()
    )
    val memoryStatus: StateFlow<MemoryStatus> = getMemoryStatusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = MemoryStatus()
    )

}