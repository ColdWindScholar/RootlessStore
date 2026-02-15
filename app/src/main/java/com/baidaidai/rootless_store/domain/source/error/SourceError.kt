package com.baidaidai.rootless_store.domain.source.error

import com.baidaidai.rootless_store.domain.error.RootlessStoreError

data class SourceError(
    override val errorMessage: String,
    override val errorCause: String
): RootlessStoreError