package com.baidaidai.rootless_store.ui.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidaidai.rootless_store.data.status.gateway.RAMStatusGatewayImpl
import com.baidaidai.rootless_store.data.status.gateway.StorageStatusGatewayImpl
import com.baidaidai.rootless_store.domain.status.model.RAMStatus
import com.baidaidai.rootless_store.domain.status.model.StorageStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootlessStoreStratScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {
//    private var context: Context? = null

    private val _storageStatus: MutableStateFlow<StorageStatus> = MutableStateFlow(getStorageStatus())
    private val _ramStatus: MutableStateFlow<RAMStatus> = MutableStateFlow(getRAMStatus())
    val storageStatus: StateFlow<StorageStatus> = _storageStatus.asStateFlow()
    val ramStatus: StateFlow<RAMStatus> = _ramStatus.asStateFlow()

    init {
        keepRamReaderRunning()
    }

//    fun prepareViewModel(
//        context: Context? = null
//    ){
//        if (context != null){
//            this.context = context
//            _upgradeThe_storageStatus()
//            _upgradeThe_RAMStatus()
//            keepRamReaderRunning()
//        }
//    }

    private fun getStorageStatus(): StorageStatus{
        return StorageStatusGatewayImpl(this.context).getStorageStatus()
//        if (this.context != null){
//            return
//        }else{
//            return StorageStatus(
//                totalStorage = 128.0,
//                usedStorage = 100.0
//            )
//        }
    }
    private fun getRAMStatus(): RAMStatus{

        return RAMStatusGatewayImpl(this.context).getRAMStatus()
//        if (this.context != null){
//        }else{
//            return RAMStatus(
//                totalRAM = 24.0,
//                usedRAM = 0.0
//            )
//        }
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

    object dbOperator{
        private fun _appendPluginInformationsIntroDatabase(){

        }
    }

    private fun keepRamReaderRunning(){
        viewModelScope.launch {
            while (true){
                _upgradeThe_RAMStatus()
                delay(1000)
                Log.d("RAM_Status","updated, current:${getRAMStatus().usedRAM}, total${getRAMStatus().totalRAM}, precentage:${(getRAMStatus().usedRAM / getRAMStatus().totalRAM*100).toFloat()}")
            }
        }
    }

}