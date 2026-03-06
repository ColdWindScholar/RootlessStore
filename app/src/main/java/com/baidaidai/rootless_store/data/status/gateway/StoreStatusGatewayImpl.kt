package com.baidaidai.rootless_store.data.status.gateway

import com.baidaidai.rootless_store.data.status.datasource.AndroidAndAPIVersionDataSource
import com.baidaidai.rootless_store.data.status.datasource.KernelStatusDataSource
import com.baidaidai.rootless_store.data.status.datasource.MemoryStatusDataSource
import com.baidaidai.rootless_store.data.status.datasource.SELinuxStatusDataSource
import com.baidaidai.rootless_store.data.status.datasource.StorageStatusDataSource
import com.baidaidai.rootless_store.data.status.datasource.TemperatureStatusDataSource
import com.baidaidai.rootless_store.domain.status.model.AndroidAndAPIStatus
import com.baidaidai.rootless_store.domain.status.model.MemoryStatus
import com.baidaidai.rootless_store.domain.status.model.SELinuxStatus
import com.baidaidai.rootless_store.domain.status.model.StorageStatus
import com.baidaidai.rootless_store.domain.status.model.TempStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreStatusGatewayImpl @Inject constructor(
    private val memoryStatusDataSource: MemoryStatusDataSource,
    private val storageStatusDataSource: StorageStatusDataSource,
    private val selinuxStatusDataSource: SELinuxStatusDataSource,
    private val kernelStatusDataSource: KernelStatusDataSource,
    private val temperatureStatusDataSource: TemperatureStatusDataSource,
    private val androidAndAPIVersionDataSource: AndroidAndAPIVersionDataSource
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

    fun getSELinuxStatus(): SELinuxStatus = selinuxStatusDataSource.returnSELinuxStatus()

    fun getKernelStatus(): String = kernelStatusDataSource.getDeviceKernel()

    fun getTemperatureStatus(): Flow<TempStatus> = temperatureStatusDataSource.getDeviceTemperatureStatus()

    fun getAndroidAndAPIStatus(): AndroidAndAPIStatus {
        val androidVersion = androidAndAPIVersionDataSource.getAndroidVersion()
        val apiVersion = androidAndAPIVersionDataSource.getAndroidAPIVersion()
        return AndroidAndAPIStatus(androidVersion,apiVersion)
    }
}