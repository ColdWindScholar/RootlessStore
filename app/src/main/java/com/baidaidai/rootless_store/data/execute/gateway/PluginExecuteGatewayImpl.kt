package com.baidaidai.rootless_store.data.execute.gateway

import com.topjohnwu.superuser.CallbackList
import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
    fun executePluginEntryPoint(pluginExecuteEntryPoint: String): Flow<String> = callbackFlow {
        val callbackList = createCallbackList { result ->
            trySend("- $result")
        }
        Shell
            .cmd("sh $pluginExecuteEntryPoint")
            .to(callbackList)
            .submit { close() }

        awaitClose {  }
    }
}