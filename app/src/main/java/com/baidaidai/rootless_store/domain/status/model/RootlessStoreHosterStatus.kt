package com.baidaidai.rootless_store.domain.status.model

data class RootlessStoreHosterStatus(
    val hosterOverallStatus: HosterOverallStatus,
    val kernelVersion: String,
    val selinuxStatus: SELinuxStatus,
    val absolutePath: String,
    val pluginStatus: PluginStatus,
    val memoryStatus: MemoryStatus,
    val storageStatus: StorageStatus,
    val tempStatus: TempStatus,
)