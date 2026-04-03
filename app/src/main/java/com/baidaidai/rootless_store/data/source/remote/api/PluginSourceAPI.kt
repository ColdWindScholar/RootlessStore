package com.baidaidai.rootless_store.data.source.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.path
import javax.inject.Inject

class PluginSourceAPI @Inject constructor(
    private val ktorClient: HttpClient
) {
    private val client = ktorClient
    suspend fun getPluginSourceMetaInfo(
        sourceRemoteEndpoint: String
    ): HttpResponse{
        return client.request(
            urlString = sourceRemoteEndpoint
        ) {
            url {
                appendPathSegments("source", "getSourceInfo")
            }
            accept(ContentType.Application.Json)
        }
    }
}