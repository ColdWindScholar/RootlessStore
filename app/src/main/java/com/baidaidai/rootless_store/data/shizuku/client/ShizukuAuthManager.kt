package com.baidaidai.rootless_store.data.shizuku.client

import android.content.pm.PackageManager
import kotlinx.coroutines.suspendCancellableCoroutine
import rikka.shizuku.Shizuku

object ShizukuAuthManager {

    private const val requestCode = 1001

    fun pingShizuku(): Boolean{
        return Shizuku.pingBinder()
    }

    suspend fun activeShizuku(): Boolean {
        if (!Shizuku.pingBinder()) return false

        if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            return true
        }

        return suspendCancellableCoroutine { continuation ->
            val listener = object : Shizuku.OnRequestPermissionResultListener {
                override fun onRequestPermissionResult(code: Int, result: Int) {
                    if (code != requestCode) return

                    Shizuku.removeRequestPermissionResultListener(this)

                    if (continuation.isActive) {
                        continuation.resume(result == PackageManager.PERMISSION_GRANTED) {}
                    }
                }
            }

            Shizuku.addRequestPermissionResultListener(listener)
            Shizuku.requestPermission(requestCode)

            continuation.invokeOnCancellation {
                Shizuku.removeRequestPermissionResultListener(listener)
            }
        }
    }

    fun checkShizukuPermission(): Boolean{
        return Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    }
}