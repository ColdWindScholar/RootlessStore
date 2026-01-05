package com.baidaidai.rootless_store.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.data.hosterstatus.impl.RAMStatusGatewayImpl
import com.baidaidai.rootless_store.data.hosterstatus.impl.StorageGatewayImpl
import com.baidaidai.rootless_store.domain.hosterstatus.model.RAMStatus
import com.baidaidai.rootless_store.domain.hosterstatus.model.StorageStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RootlessStoreStratScreenViewModel: ViewModel() {
    private var context: Context? = null

    private val _storageStatus: MutableStateFlow<StorageStatus> = MutableStateFlow(getStorageStatus())
    private val _ramStatus: MutableStateFlow<RAMStatus> = MutableStateFlow(getRAMStatus())
    val storageStatus: StateFlow<StorageStatus> = _storageStatus.asStateFlow()
    val ramStatus: StateFlow<RAMStatus> = _ramStatus.asStateFlow()


    fun prepareViewModel(
        context: Context? = null
    ){
        if (context != null){
            this.context = context
            _upgradeThe_storageStatus()
            _upgradeThe_RAMStatus()
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
    private fun getRAMStatus(): RAMStatus{
        if (this.context != null){
            return RAMStatusGatewayImpl(this.context!!).getRAMStatus()
        }else{
            return RAMStatus(
                totalRAM = 24.0,
                usedRAM = 0.0
            )
        }
    }
    private fun _upgradeThe_storageStatus(){
        this._storageStatus.update {
            getStorageStatus()
        }
    }
    private fun _upgradeThe_RAMStatus(){
        this._ramStatus.update {
            getRAMStatus()
        }
    }

}