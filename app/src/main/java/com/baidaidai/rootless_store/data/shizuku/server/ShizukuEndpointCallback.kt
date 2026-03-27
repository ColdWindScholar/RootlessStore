package com.baidaidai.rootless_store.data.shizuku.server

class ShizukuEndpointCallback(
    private val onExecuteCallback:(String?)-> Unit
):IShellCallback.Stub() {
    override fun onExecute(session: String?) {
        onExecuteCallback(session)
    }
}