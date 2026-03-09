package com.baidaidai.rootless_store.data.status.datasource

import android.util.Log
import com.baidaidai.rootless_store.domain.status.model.SELinuxStatus
import com.topjohnwu.superuser.Shell
import javax.inject.Inject

class SELinuxStatusDataSource @Inject constructor() {
    fun returnSELinuxStatus(): SELinuxStatus{
        val shell = Shell.Builder.create()
            .setFlags(Shell.FLAG_REDIRECT_STDERR)
            .build("sh")

        val list = ArrayList<String>()
        val result = shell.use {
            it.newJob().add("getenforce").to(list, list).exec()
        }
        val output = result.out.joinToString("\n").trim()

        if (result.isSuccess) {
            return when (output) {
                "Enforcing" -> SELinuxStatus.Enforcing
                "Permissive" -> SELinuxStatus.Permissive
                "Disabled" -> SELinuxStatus.Disabled
                else -> SELinuxStatus.Unknow
            }
        }

        return if (output.endsWith("Permission denied")) {
            SELinuxStatus.Enforcing
        } else {
            Log.d("err",result.err.isEmpty().toString())
            Log.d("out",result.out.isEmpty().toString())
            SELinuxStatus.Unknow
        }
    }
}