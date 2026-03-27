package com.baidaidai.rootless_store.ui.model

import IShellService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.core.util.OutOfStringLike
import com.baidaidai.rootless_store.data.shizuku.repository.ShizukuAdbRepositoryImpl
import com.baidaidai.rootless_store.domain.plugin.error.PluginError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootlessStoreShizukuAdbScreenViewModel @Inject constructor(
    private val shizukuAdbRepositoryImpl: ShizukuAdbRepositoryImpl
): ViewModel() {

    private val _shizukuActived = MutableStateFlow(false)
    private val _endpointActived = MutableStateFlow(false)
    val shizukuActived = _shizukuActived.asStateFlow()
    val endpointActived = _endpointActived.asStateFlow()

    private val _shizukuEvent = MutableSharedFlow<PluginError?>()
    val shizukuEvent = _shizukuEvent.asSharedFlow()

    fun activeShizuku() = viewModelScope.launch {
        try {
            if(shizukuAdbRepositoryImpl.getShizukuAuthStatus() || shizukuAdbRepositoryImpl.getShizukuAuth()){
                _shizukuActived.value = true
            }else{
                _shizukuActived.value = false
            }
        }catch (error: Throwable){
            _shizukuEvent.emit(
                PluginError(errorMessage = error.message!!, errorCause = error.stackTrace.OutOfStringLike())
            )
        }
    }
    fun activeShizukuEndpoint() {
        if (shizukuAdbRepositoryImpl.connectShizukuEndpoint()) {
            _endpointActived.value = true
        }
    }

    fun onOkButtonClick() = viewModelScope.launch {
        _shizukuEvent.emit(null)
    }
}