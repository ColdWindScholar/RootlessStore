package com.baidaidai.rootless_store.domain.source.error

sealed interface SourceError {

    val errorMessage: String
    val errorCause: String

    interface DomainError: SourceError
    interface TimeoutError: SourceError
    interface RefuseError: SourceError
    interface ConnectionError: SourceError
}