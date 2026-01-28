package com.baidaidai.rootless_store.domain.status.gateway

import com.baidaidai.rootless_store.domain.status.model.RAMStatus

interface RAMStatusGateway{
    fun getRAMStatus(): RAMStatus
}