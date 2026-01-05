package com.baidaidai.rootless_store.domain.hosterstatus.gateway

import com.baidaidai.rootless_store.domain.hosterstatus.model.RAMStatus

interface RAMStatusGateway{
    fun getRAMStatus(): RAMStatus
}
