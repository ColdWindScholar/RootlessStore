package com.baidaidai.rootless_store.components.stratScreen.model

import com.baidaidai.rootless_store.domain.hosterstatus.model.StorageStatus
import com.baidaidai.rootless_store.domain.hosterstatus.model.RAMStatus

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
