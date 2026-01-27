package com.baidaidai.rootless_store.data.status.impl

import android.content.Context
import com.baidaidai.rootless_store.data.status.datasource.StorageInfoReader
import com.baidaidai.rootless_store.domain.plugin.gateway.StorageStatusGetAway
import com.baidaidai.rootless_store.domain.plugin.model.StorageStatus

class StorageStatusGatewayImpl(
    private val context: Context
) : StorageStatusGetAway {
    override fun getStorageStatus(): StorageStatus {
        val reader = StorageInfoReader(appContext = context)
        val usedStorage = reader.getUsedStorage()
        val totalStorage = reader.getTotalStorage()
        return StorageStatus(totalStorage,usedStorage)
    }
}
