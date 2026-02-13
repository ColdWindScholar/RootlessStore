package com.baidaidai.rootless_store.domain.source.error

data class SourceError(
    val errorMessage: String,
    val errorCause: String
)