package com.baidaidai.rootless_store.domain.plugin.model

import kotlinx.serialization.Serializable

@Serializable
enum class HosterOverallStatus{
    LIMITED,ADB,ROOTD
}