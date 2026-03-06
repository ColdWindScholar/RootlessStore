package com.baidaidai.rootless_store.domain.status.model

data class RootlessStoreHosterStatus(
    val hosterOverallStatus: HosterOverallStatus? = null,
    val osAndAPIVersion: AndroidAndAPIStatus? = null,
    val kernelVersion: String? = null,
    val selinuxStatus: SELinuxStatus = SELinuxStatus.Unknow,
    val pluginStatus: PluginStatus = PluginStatus(
        enabledCount = 0,
        totalCount = 0
    ),
    val memoryStatus: MemoryStatus = MemoryStatus(),
    val storageStatus: StorageStatus = StorageStatus(),
    val tempStatus: TempStatus? = null,
)