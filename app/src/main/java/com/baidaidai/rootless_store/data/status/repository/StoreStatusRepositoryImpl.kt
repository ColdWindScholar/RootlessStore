package com.baidaidai.rootless_store.data.status.repository

import com.baidaidai.rootless_store.data.status.gateway.StoreStatusGatewayImpl
import com.baidaidai.rootless_store.domain.status.model.MemoryStatus
import com.baidaidai.rootless_store.domain.status.model.StorageStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreStatusRepositoryImpl @Inject constructor(
    private val storeStatusGatewayImpl: StoreStatusGatewayImpl
) {

    fun getStorageStatus(): Flow<StorageStatus> = storeStatusGatewayImpl.getStorageStatus()

    fun getMemoryStatus(): Flow<MemoryStatus> = storeStatusGatewayImpl.getMemoryStatus()
}
