package com.baidaidai.rootless_store.domain.plugin.gateway

import com.baidaidai.rootless_store.domain.plugin.model.StorageStatus

interface StorageStatusGetAway {
    fun getStorageStatus(): StorageStatus
}