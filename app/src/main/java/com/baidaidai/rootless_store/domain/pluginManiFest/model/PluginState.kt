package com.baidaidai.rootless_store.domain.pluginManiFest.model

import kotlinx.serialization.Serializable

@Serializable
enum class PluginState {
    Great,PermissionProblems,PluginRuntimeProblems,RootlessStoreRuntimeProblems
}