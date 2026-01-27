package com.baidaidai.rootless_store.data.status.datasource

import android.app.ActivityManager
import android.content.Context

class RAMInfoReader(
    private val appContext: Context
) {
    private val am = appContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val mi = ActivityManager.MemoryInfo().also { am.getMemoryInfo(it) }

    fun getTotalRAM(): Double{
        val totalRamBytes = mi.totalMem
        return reverseBytesToGiB(totalRamBytes)
    }
    fun getUsedRAM(): Double{
        val usedRamBytes = mi.totalMem - mi.availMem
        return reverseBytesToGiB(usedRamBytes)
    }
    private fun reverseBytesToGiB(bytes: Long): Double {
        return bytes / (1024.0 * 1024.0 * 1024.0)
    }
}