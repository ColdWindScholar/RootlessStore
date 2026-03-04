package com.baidaidai.rootless_store.data.status.gateway

import com.baidaidai.rootless_store.data.status.datasource.MemoryStatusDataSource
import com.baidaidai.rootless_store.data.status.datasource.StorageStatusDataSource
import com.baidaidai.rootless_store.domain.status.model.MemoryStatus
import com.baidaidai.rootless_store.domain.status.model.StorageStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreStatusGatewayImpl @Inject constructor(
    private val memoryStatusDataSource: MemoryStatusDataSource,
    private val storageStatusDataSource: StorageStatusDataSource
) {
    fun getMemoryStatus(): Flow<MemoryStatus> = flow {
        while (true){
            val totalMemory = memoryStatusDataSource.getTotalMemory()
            val usedMemory = memoryStatusDataSource.getUsedMemory()
            emit(MemoryStatus(totalMemory,usedMemory))
            delay(100)
        }
    }

    fun getStorageStatus(): Flow<StorageStatus> = flow {
        while (true){
            val usedStorage = storageStatusDataSource.getUsedStorage()
            val totalStorage = storageStatusDataSource.getTotalStorage()
            emit(StorageStatus(totalStorage,usedStorage))
            delay(1000)
        }
    }
}