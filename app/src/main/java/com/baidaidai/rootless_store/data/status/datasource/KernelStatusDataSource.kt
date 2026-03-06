package com.baidaidai.rootless_store.data.status.datasource

import com.topjohnwu.superuser.Shell
import javax.inject.Inject

class KernelStatusDataSource @Inject constructor(

) {
    fun getDeviceKernel(): String{
        return Shell.cmd("uname -r").exec().out.joinToString().substringBefore("-")
    }
}