package com.baidaidai.rootless_store.domain.source.error

import kotlinx.serialization.Serializable

@Serializable
data class TimeoutError(
    override val errorCause: String,
    override val errorMessage: String
) : SourceError.TimeoutError
