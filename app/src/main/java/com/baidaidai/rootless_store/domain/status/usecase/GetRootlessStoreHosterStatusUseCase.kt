package com.baidaidai.rootless_store.domain.status.usecase

import com.baidaidai.rootless_store.domain.status.model.HosterOverallStatus
import com.baidaidai.rootless_store.domain.status.model.RootlessStoreHosterStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetRootlessStoreHosterStatusUseCase @Inject constructor(
    private val getMemoryStatusUseCase: GetMemoryStatusUseCase,
    private val getStorageStatusUseCase: GetStorageStatusUseCase,
    private val getPluginStatusUseCase: GetPluginStatusUseCase,
    private val getSELinuxUseCase: GetSELinuxUseCase,
    private val getKernelStatusUseCase: GetKernelStatusUseCase,
    private val getTemperatureStatusUseCase: GetTemperatureStatusUseCase,
    private val getAndroidAndAPIStatusUseCase: GetAndroidAndAPIStatusUseCase
) {
    operator fun invoke(): Flow<RootlessStoreHosterStatus> = combine(
        getMemoryStatusUseCase(),
        getStorageStatusUseCase(),
        getPluginStatusUseCase(),
        getTemperatureStatusUseCase()
    ) { memoryStatus, storageStatus, pluginStatus, temperatureStatus ->
        RootlessStoreHosterStatus(
            hosterOverallStatus = HosterOverallStatus.LIMITED,
            kernelVersion = getKernelStatusUseCase(),
            selinuxStatus = getSELinuxUseCase(),
            pluginStatus = pluginStatus,
            memoryStatus = memoryStatus,
            storageStatus = storageStatus,
            tempStatus = temperatureStatus,
            osAndAPIVersion = getAndroidAndAPIStatusUseCase()
        )
    }
}
