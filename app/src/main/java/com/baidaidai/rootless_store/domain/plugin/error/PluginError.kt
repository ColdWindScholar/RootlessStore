package com.baidaidai.rootless_store.domain.plugin.error

sealed interface PluginError {
    val errorMessage: String

    interface ManifestError: PluginError
}