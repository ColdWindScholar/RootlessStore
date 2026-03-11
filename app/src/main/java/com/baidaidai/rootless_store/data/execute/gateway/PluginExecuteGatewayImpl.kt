package com.baidaidai.rootless_store.data.execute.gateway

import com.topjohnwu.superuser.CallbackList
import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class PluginExecuteGatewayImpl @Inject constructor(
) {

    internal fun createCallbackList(
        onAddingElement: (String) -> Unit
    ): CallbackList<String> {
        return object : CallbackList<String>() {
            override fun onAddElement(s: String) = onAddingElement(s)
        }
    }

    internal fun rootEnvironmentSwitch(): String{
        val shell = Shell.getShell()
        return if (shell.isRoot){
            "su"
        }else{
            "sh"
        }
    }
    fun executePluginEntryPoint(pluginExecuteEntryPoint: String,pluginPackageDirectory: String): Flow<String> = callbackFlow {
        val process = ProcessBuilder(
            rootEnvironmentSwitch(), "-c", "cd $pluginPackageDirectory ;echo PID:$$; exec $pluginExecuteEntryPoint"
        )
            .redirectErrorStream(true) // stderr 合并到 stdout
            .start()

        launch(Dispatchers.IO) {
            process.inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    send("- $line")
                }
            }
        }

        awaitClose {
        }
    }
        .flowOn(Dispatchers.IO)

    fun abortPluginProcess(pluginProcessPID: Int?){
        if (pluginProcessPID != null){
            ProcessBuilder(
                rootEnvironmentSwitch(), "-c", "kill $pluginProcessPID"
            ).start()
        }
    }
}