package com.baidaidai.rootless_store.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.domain.source.error.SourceError
import com.baidaidai.rootless_store.domain.source.usecase.AddOneSourceUseCase
import com.baidaidai.rootless_store.domain.source.usecase.GetPluginSourceCountUseCase
import com.baidaidai.rootless_store.domain.source.usecase.GetWholeSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootLessStoreSourceScreenViewModel @Inject constructor(
    getWholeSourceUseCase: GetWholeSourceUseCase,
    getPluginSourceCountUseCase: GetPluginSourceCountUseCase,
    private val addOneSourceUseCase: AddOneSourceUseCase
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

    fun addOneSource(
        sourceURI: String
    ){
        viewModelScope.launch {
            addOneSourceUseCase(sourceURI)
            val result = addOneSourceUseCase(sourceURI)

            if(result is SourceError){
                _sourceEvent.emit(
                    result
                )
            }else{
                _sourceEvent.emit(null)
            }
        }
    }
        }
    }
}