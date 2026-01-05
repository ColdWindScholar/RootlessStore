package com.baidaidai.rootless_store.model

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.baidaidai.rootless_store.data.hosterstatus.impl.StorageGatewayImpl
import com.baidaidai.rootless_store.domain.hosterstatus.model.StorageStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RootlessStoreStratScreenViewModel: ViewModel() {
    private var context: Context? = null

    private val _storageStatus: MutableStateFlow<StorageStatus> = MutableStateFlow(getStorageStatus())
    val storageStatus: StateFlow<StorageStatus> = _storageStatus.asStateFlow()


    fun prepareViewModel(
        context: Context? = null
    ){
        if (context != null){
            this.context = context
            _upgradeThe_storageStatus()
        }
    }

    private fun getStorageStatus(): StorageStatus{
        if (this.context != null){
            return StorageGatewayImpl(this.context!!).getStorageStatus()
        }else{
            return StorageStatus(
                totalStorage = 128.0,
                usedStorage = 100.0
            )
        }
    }
    private fun _upgradeThe_storageStatus(){
        this._storageStatus.update {
            getStorageStatus()
        }
    }
}