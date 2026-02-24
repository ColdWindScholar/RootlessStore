package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.domain.source.error.SourceError
import com.baidaidai.rootless_store.domain.source.model.PluginSourceLocal
import com.baidaidai.rootless_store.domain.source.usecase.AddOneSourceUseCase
import com.baidaidai.rootless_store.domain.source.usecase.DeleteOneSourceUseCase
import com.baidaidai.rootless_store.domain.source.usecase.GetPluginSourceCountUseCase
import com.baidaidai.rootless_store.domain.source.usecase.GetWholeSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootLessStoreSourceScreenViewModel @Inject constructor(
    getWholeSourceUseCase: GetWholeSourceUseCase,
    getPluginSourceCountUseCase: GetPluginSourceCountUseCase,
    private val addOneSourceUseCase: AddOneSourceUseCase,
    private val deleteOneSourceUseCase: DeleteOneSourceUseCase
): ViewModel(){

    val sourceList = getWholeSourceUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val sourceCount = getPluginSourceCountUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    val _sourceEvent = MutableSharedFlow<SourceError?>()
    val sourceEvent = _sourceEvent.asSharedFlow()

    private val _deleterShowStatus = MutableStateFlow(false)
    val deleterShowStatus = _deleterShowStatus.asStateFlow()

    fun addOneSource(
        sourceURI: String
    ){
        viewModelScope.launch {
            val result = addOneSourceUseCase(sourceURI)

            if(result is SourceError){
                _sourceEvent.emit(result)
            }else{
                _sourceEvent.emit(null)
            }
        }
    }
    fun onOkButtonClick(){
        viewModelScope.launch {
            _sourceEvent.emit(null)
        }
    }

    fun deleteOneSource(
        pluginSourceLocal: PluginSourceLocal
    ){
        viewModelScope.launch {
            deleteOneSourceUseCase(pluginSourceLocal)
        }
    }
    fun changeDeleterShowStatus(){
        _deleterShowStatus.update {
            !it
        }
    }
}