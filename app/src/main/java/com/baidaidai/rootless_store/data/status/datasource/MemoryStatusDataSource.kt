package com.baidaidai.rootless_store.data.status.datasource

import android.app.ActivityManager
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MemoryStatusDataSource @Inject constructor(
    @ApplicationContext context: Context
) {
    private val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    private fun currentMemoryInfo(): ActivityManager.MemoryInfo {
        return ActivityManager.MemoryInfo().also { am.getMemoryInfo(it) }
    }

    fun getTotalMemory(): Double{
        val totalRamBytes = currentMemoryInfo().totalMem
        return reverseBytesToGiB(totalRamBytes)
    }
    fun getUsedMemory(): Double{
        val usedRamBytes = currentMemoryInfo().totalMem - currentMemoryInfo().availMem
        return reverseBytesToGiB(usedRamBytes)
    }
    private fun reverseBytesToGiB(bytes: Long): Double {
        return bytes / (1024.0 * 1024.0 * 1024.0)
    }
}