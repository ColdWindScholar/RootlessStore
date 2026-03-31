package com.baidaidai.rootless_store.data.execute.gateway

import android.util.Log
import com.baidaidai.rootless_store.data.shizuku.repository.ShizukuAdbRepositoryImpl
import com.baidaidai.rootless_store.data.shizuku.server.ShizukuEndpointCallback
import com.baidaidai.rootless_store.domain.execute.model.ExecuteResult
import com.baidaidai.rootless_store.domain.execute.model.ResultTag
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
    private val shizukuAdbRepositoryImpl: ShizukuAdbRepositoryImpl,
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
    fun executePluginEntryPoint(pluginExecuteEntryPoint: String,pluginPackageDirectory: String): Flow<ExecuteResult> = callbackFlow {
        val process = ProcessBuilder(
            rootEnvironmentSwitch(), "-c", "cd $pluginPackageDirectory ;echo PID:$$; exec $pluginExecuteEntryPoint"
        ).start()

        launch(Dispatchers.IO) {
            process.inputStream.bufferedReader().useLines { lines ->
                lines.forEach { result ->
                    send(
                        ExecuteResult(
                            resulTag = ResultTag.Normal,
                            content = "- ${result.toString()}"
                        )
                    )
                }
            }
            process.errorStream.bufferedReader().useLines { lines ->
                lines.forEach { error ->
                    send(
                        ExecuteResult(
                            resulTag = ResultTag.Normal,
                            content = "- ${error.toString()}"
                        )
                    )
                }
            }
        }

        awaitClose {
        }
    }
        .flowOn(Dispatchers.IO)

    fun executePluginEntryPointByShizuku(pluginExecuteEntryPoint: String,pluginPackageDirectory: String): Flow<ExecuteResult> =

        callbackFlow {
            launch(Dispatchers.IO) {
                val callback = ShizukuEndpointCallback(
                    onExecuteCallback = { session ->
                        trySend(
                            ExecuteResult(
                                resulTag = ResultTag.Normal,
                                content = "- ${session.toString()}"
                            )
                        )
                    },
                    onErrorCallback = { error ->
                        trySend(
                            ExecuteResult(
                                resulTag = ResultTag.RedLine,
                                content = "- ${error.toString()}"
                            )
                        )
                    }
                )

                Log.d("exam",(shizukuAdbRepositoryImpl.getShizukuEndpoint()==null).toString())

                shizukuAdbRepositoryImpl.getShizukuEndpoint()
                    ?.exec(
                        pluginExecuteEntryPoint,
                        pluginPackageDirectory,
                        callback
                    )
            }
            awaitClose {  }
        }

    fun abortPluginProcess(pluginProcessPID: Int?){
        if (pluginProcessPID != null){
            ProcessBuilder(
                rootEnvironmentSwitch(), "-c", "kill $pluginProcessPID"
            ).start()
        }
    }

    fun abortPluginProcessByShizuku(pluginProcessPID: Int?){
        if (pluginProcessPID != null){
            Log.d("exam","shizuku ${shizukuAdbRepositoryImpl.getShizukuEndpoint() == null}")
            Log.d("pid","$pluginProcessPID")

            val result = shizukuAdbRepositoryImpl.getShizukuEndpoint()
                ?.kill(pluginProcessPID)

            Log.d("kill pid result",result.toString())
        }
    }
}