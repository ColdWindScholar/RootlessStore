package com.baidaidai.rootless_store.data.hosterstatus.impl

import android.content.Context
import com.baidaidai.rootless_store.data.hosterstatus.RAM.RAMInfoReader
import com.baidaidai.rootless_store.domain.hosterstatus.gateway.RAMStatusGateway
import com.baidaidai.rootless_store.domain.hosterstatus.model.RAMStatus

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