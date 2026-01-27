package com.baidaidai.rootless_store.domain.plugin.gateway

import com.baidaidai.rootless_store.domain.plugin.model.RAMStatus

interface RAMStatusGateway{
    fun getRAMStatus(): RAMStatus
}
