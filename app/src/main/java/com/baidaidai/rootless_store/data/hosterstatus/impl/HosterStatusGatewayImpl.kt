package com.baidaidai.rootless_store.data.hosterstatus.impl

import android.content.Context
import com.baidaidai.rootless_store.data.hosterstatus.storage.StorageInfoReader
import com.baidaidai.rootless_store.domain.hosterstatus.gateway.StorageGetAway
import com.baidaidai.rootless_store.domain.hosterstatus.model.StorageStatus

class StorageGatewayImpl(
    private val context: Context
) : StorageGetAway {
    override fun getStorageStatus(): StorageStatus {
        val reader = StorageInfoReader(appContext = context)
        val usedStorage = reader.getUsedStorage()
        val totalStorage = reader.getTotalStorage()
        return StorageStatus(totalStorage,usedStorage)
    }
}
