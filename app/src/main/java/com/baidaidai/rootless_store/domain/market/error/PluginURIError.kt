package com.baidaidai.rootless_store.domain.market.error

import kotlinx.serialization.Serializable

@Serializable
data class PluginURIError(
    override val errorMessage: String
) : MarketError.PluginURIError
