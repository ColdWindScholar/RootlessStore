package com.baidaidai.rootless_store.domain.source.error

data class ConnectionError(
    override val errorMessage: String,
    override val errorCause: String
): SourceError.ConnectionError
