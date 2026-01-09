package com.baidaidai.rootless_store.domain.hosterstatus.gateway

import com.baidaidai.rootless_store.domain.hosterstatus.model.StorageStatus

interface StorageStatusGetAway {
    fun getStorageStatus(): StorageStatus
}