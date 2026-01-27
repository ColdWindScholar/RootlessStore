package com.baidaidai.rootless_store.data.hosterstatus.impl

import android.content.Context
import com.baidaidai.rootless_store.data.hosterstatus.storage.StorageInfoReader
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
