package com.baidaidai.rootless_store.domain.market.error

sealed interface MarketError {
    val errorMessage: String

    interface ListContentManifestError: MarketError
    interface PluginInstallError: MarketError
    interface PluginURIError: MarketError
}