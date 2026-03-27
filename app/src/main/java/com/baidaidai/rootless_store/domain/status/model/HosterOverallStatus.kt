package com.baidaidai.rootless_store.domain.status.model

import kotlinx.serialization.Serializable

@Serializable
enum class HosterOverallStatus{
    LIMITED,PERMISSIVE,ADB,ROOTD
}