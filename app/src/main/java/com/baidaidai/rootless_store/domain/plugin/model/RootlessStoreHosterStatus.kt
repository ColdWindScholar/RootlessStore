package com.baidaidai.rootless_store.domain.plugin.model

data class RootlessStoreHosterStatus(
    val hosterOverallStatus: HosterOverallStatus,
    val kernelVersion: String,
    val selinuxStatus: SELinuxStatus,
    val absolutePath: String,
    val pluginStatus: PluginStatus,
    val ramStatus: RAMStatus,
    val storageStatus: StorageStatus,
    val tempStatus: TempStatus,
)
