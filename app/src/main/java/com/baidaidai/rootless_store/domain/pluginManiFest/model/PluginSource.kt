package com.baidaidai.rootless_store.domain.pluginManiFest.model

import kotlinx.serialization.Serializable

@Serializable
enum class PluginSource {
    Official, Third, Local
}