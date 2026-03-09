package com.baidaidai.rootless_store.data.status.datasource

import android.content.Context
import android.os.StatFs
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StorageStatusDataSource @Inject constructor(
    @ApplicationContext appContext: Context
){
    private val statFS = StatFs(appContext.dataDir.absolutePath)
    fun getUsedStorage(): Double{
        val usedStorage: Long = statFS.totalBytes - statFS.availableBytes
        return reverseBytesToGiB(usedStorage)
    }
    fun getTotalStorage(): Double{
        val totalStorage: Long = statFS.totalBytes
        return reverseBytesToGiB(totalStorage)
    }

    private fun reverseBytesToGiB(bytes: Long): Double {
        return bytes / (1024.0 * 1024.0 * 1024.0)
    }
}