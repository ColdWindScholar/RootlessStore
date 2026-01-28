package com.baidaidai.rootless_store.data.status.gateway

import android.content.Context
import com.baidaidai.rootless_store.data.status.datasource.RAMInfoReader
import com.baidaidai.rootless_store.domain.status.gateway.RAMStatusGateway
import com.baidaidai.rootless_store.domain.status.model.RAMStatus

class RAMStatusGatewayImpl(
    private val context: Context
): RAMStatusGateway {
    override fun getRAMStatus(): RAMStatus {
        val reader = RAMInfoReader(appContext = context)
        val totalRAM = reader.getTotalRAM()
        val usedRam = reader.getUsedRAM()
        return RAMStatus(totalRAM,usedRam)
    }
}